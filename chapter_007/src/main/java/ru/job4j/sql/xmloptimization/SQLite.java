package ru.job4j.sql.xmloptimization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@XmlRootElement
public class SQLite {

    //адрес при подключении к бд
    private String url;
    //количество записей добавляемых в бд
    private int n;
    //соединение с бд
    private Connection conn;
    //xml файл с бд
    private File xml1;
    //xml файл преобразованный с помощью xslt
    private File xml2;
    //xls файл для преобразования xml файла
    private File xslFile;

    private static final Logger log = LoggerFactory.getLogger(SQLite.class);

    public SQLite(String url, int n) {
        this.url = url;
        this.n = n;
    }

    public void setXml1(File xml1) {
        this.xml1 = xml1;
    }

    public void setXml2(File xml2) {
        this.xml2 = xml2;
    }

    public void setXslFile(File xslFile) {
        this.xslFile = xslFile;
    }

    //соединение с бд
    public void open () {
        try {
            this.conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            if (conn == null)
                System.exit(0);
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS TEST(id INTEGER PRIMARY KEY, FIELD INTEGER)").executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    //добавление данных в бд
    public void insert() {
        Savepoint savePointOne = null;
        try {
            savePointOne = conn.setSavepoint("SavePointOne");
            this.conn.prepareStatement("DELETE FROM TEST").executeUpdate();
            PreparedStatement st = conn.prepareStatement("insert into TEST(FIELD) values(?)",
                    Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= n; i++) {
                st.setInt(1, i);
                st.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            try {
                conn.rollback(savePointOne);
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }
        }
    }

    public Field field(String value) {
        Field field = new Field();
        field.setField(value);
        return field;
    }

    //добавляем данные с помощью технологии stax
    public void createXmlDocumentByStax() {
        try  {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(xml1));
            writer.writeStartDocument("UTF-8", "1.1");
            writer.writeCharacters("\n");
            writer.writeStartElement("entries");
            writer.writeCharacters("\n");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM TEST");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                writer.writeStartElement("entry");
                writer.writeCharacters("\n");
                writer.writeStartElement("field");
                writer.writeCharacters(rs.getString("FIELD"));
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeEndElement();
                writer.writeCharacters("\n");
            }
            writer.writeCharacters("\n");
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            log.error(e.getMessage(), e);
        } catch (SQLException sqle) {
            log.error(sqle.getMessage(), sqle);
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
        }
    }

    //добавляем данные с помощью технологии jaxb
    public void createXmlDocByJaxb() {
        Entries entries = new Entries();
        entries.setEntry(new ArrayList<>(n));
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PreparedStatement pst = conn.prepareStatement("SELECT * FROM TEST");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                entries.getEntry().add(field(rs.getString("FIELD")));
            }
            marshaller.marshal(entries, xml1);
        } catch (JAXBException e) {
            log.error(e.getMessage(), e);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    //добавляем данные с помощью технологии dom
    public void createXmlDocByDom() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement =
                    doc.createElement("entries");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);
            // добавляем первый дочерний элемент к корневому
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM TEST");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Element entry = doc.createElement("entry");
                Element node = doc.createElement("field");
                node.appendChild(doc.createTextNode(rs.getString("FIELD")));
                entry.appendChild(node);
                rootElement.appendChild(entry);
            }
            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            //печатаем в файл
            StreamResult file = new StreamResult(xml1);
            //записываем данные
            transformer.transform(source, file);
        } catch (ParserConfigurationException e) {
            log.error(e.getMessage(), e);
        } catch (TransformerException te) {
            log.error(te.getMessage(), te);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    //преобразуем xml1 файл с помошью xslt в xml2
    public void transformationXMLinXLST() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
            StreamSource xml = new StreamSource(xml1);
            StreamResult result = new StreamResult(xml2);
            transformer.transform(xml, result);
        } catch (TransformerException e) {
            log.error(e.getMessage(), e);
        }
    }

    //закрываем соединение с бд
    public void close() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }

    }
}


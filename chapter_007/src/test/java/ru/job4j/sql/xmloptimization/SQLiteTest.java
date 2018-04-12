package ru.job4j.sql.xmloptimization;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

public class SQLiteTest {

    private final String xml1 = "1.xml";
    private final String xml2 = "2.xml";
    private final String xslFile = "xs.xsl";
    private final String URL = "jdbc:sqlite:TEST.FIELD";
    private SQLite sqLite = new SQLite(URL, 1000000);

    //Расчитывем время выполнения.
    @Test
    public void whenMarkTime() {
            long l1 = System.currentTimeMillis();
            whenInsertDateInTable();
            whenCreateXmlDocumentByStaxAndTransformationInXlst();
            whenParsingXmlWithSax();
            long totalTime = System.currentTimeMillis() - l1;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(totalTime);
            long sec = calendar.get(Calendar.SECOND);
            System.out.println(sec);
    }

    //Добавляем данные в бд.
    @Test
    public void whenInsertDateInTable() {
        sqLite.setXml1(new File(xml1));
        sqLite.setXml2(new File(xml2));
        sqLite.setXslFile(new File(xslFile));
        sqLite.open();
        sqLite.insert();
    }

    //Создаем xml документ с помощью stax технологии и трансформируем с помощью xlst.
    @Test
    public void whenCreateXmlDocumentByStaxAndTransformationInXlst() {
        sqLite.setXml1(new File(xml1));
        sqLite.setXml2(new File(xml2));
        sqLite.setXslFile(new File(xslFile));
        sqLite.open();
        sqLite.createXmlDocumentByStax();
        sqLite.transformationXMLinXLST();
    }

    //Создаем xml документ с помощью Dom технологии и трансформируем с помощью xlst.
    @Test
    public void whenCreateXmlDocumentByDomAndTransformationInXlst() {
        sqLite.setXml1(new File(xml1));
        sqLite.setXml2(new File(xml2));
        sqLite.setXslFile(new File(xslFile));
        sqLite.open();
        sqLite.createXmlDocByDom();
        sqLite.transformationXMLinXLST();
    }

    //Создаем xml документ с помощью Jaxb технологии и трансформируем с помощью xlst.
    @Test
    public void CreateXmlDocumentByJaxbAndTransformationInXlst() {
        sqLite.setXml1(new File(xml1));
        sqLite.setXml2(new File(xml2));
        sqLite.setXslFile(new File(xslFile));
        sqLite.open();
        sqLite.createXmlDocByJaxb();
        sqLite.transformationXMLinXLST();
    }

    //Парсируем xml2 с помощью sax.
    @Test
    public void whenParsingXmlWithSax() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Handler sp = new Handler();
        try {
            SAXParser saxParser = factory.newSAXParser();
            try {
                saxParser.parse(xml2, sp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        System.out.println(sp.getCount());
    }
}
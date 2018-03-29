package ru.job4j.sql;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.XMLEvent;
public class MainClass {
    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(System.out);
        writer.add(eventFactory.createStartDocument("UTF-8", "1.0"));
        writer.add(eventFactory.createStartElement(new QName("p"), null, null));
        XMLEvent sampleElement = eventFactory.createStartElement("", null, "s", null, null);
        writer.add(sampleElement);
        writer.add(eventFactory.createEndElement("", null, "s"));
        writer.add(sampleElement);
        writer.add(eventFactory.createEndDocument());
        writer.flush();
    }
}

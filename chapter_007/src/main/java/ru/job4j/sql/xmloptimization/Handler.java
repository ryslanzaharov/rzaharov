package ru.job4j.sql.xmloptimization;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    private long count = 0;

    private static String ENTRY = "entry";

    public long getCount() {
        return count;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing.");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parsing.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (ENTRY.equals(qName))
            count += Integer.parseInt(attributes.getValue("field"));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

}

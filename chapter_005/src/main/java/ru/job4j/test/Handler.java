package ru.job4j.test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {
    Order order;
    OrderBook orderBook = new OrderBook();

    Handler(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Если тэг имеет имя NAME, то мы этот момент отмечаем - начался тэг NAME
        if (qName.equals("AddOrder")) {
             order = new Order(attributes.getValue(0),
                    attributes.getValue(1),
                    Double.parseDouble(attributes.getValue(2).trim()),
                    Integer.parseInt(attributes.getValue(3)),
                    Integer.parseInt(attributes.getValue(4)));
                    orderBook.addOrDeleteOrder(order, true);
        }
        else if (qName.equals("DeleteOrder")){
            order = new Order(attributes.getValue(0),
                              Integer.parseInt(attributes.getValue(1)));
            orderBook.addOrDeleteOrder(order, false);
        }
    }
}

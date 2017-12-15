package ru.job4j.test;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class OrderBook {
    //Все заявки
    Map<String, HashMap<Integer, Order>> orders = new HashMap<>();

    HashMap<Integer, Order> map;
    //Отсортированные заявки на покупку и продажу
    HashMap<String, TreeMap<Double, Order>> buys = new HashMap<>();
    HashMap<String, TreeMap<Double, Order>> sells = new HashMap<>();
    //Результат заявок
    TreeMap<String, ArrayList<Order>> resultBuys = new TreeMap<>();
    TreeMap<String, ArrayList<Order>> resultSells = new TreeMap<>();

    public void parsing() {
        // Имя файла
        final String fileName = "C:\\projects\\rzaharov\\orders.xml";
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(fileName, new Handler(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Добавление или удаление заявок
    public void addOrDeleteOrder(Order order, boolean flag) {
        orders.putIfAbsent(order.getBook(), new HashMap<>());
        map = orders.get(order.getBook());
        if (flag) {
            map.put(order.getOrderId(), order);
        }
        else
            map.remove(order.getOrderId());
    }

    //Агрегирование и сортировка заявок
    public void aggregated() {
        for (Map.Entry<String, HashMap<Integer, Order>> map : orders.entrySet()) {
            buys.putIfAbsent(map.getKey(), new TreeMap<>(
                    new Comparator<Double>() {
                        @Override
                        public int compare(Double o1, Double o2) {
                            return o2.compareTo(o1);
                        }
                    }
            ));
            sells.putIfAbsent(map.getKey(), new TreeMap<>(
                    new Comparator<Double>() {
                        @Override
                        public int compare(Double o1, Double o2) {
                            return o1.compareTo(o2);
                        }
                    }
            ));
            for (Order order : map.getValue().values()) {
                TreeMap<Double, Order> setBuy = buys.get(order.getBook());
                TreeMap<Double, Order> setSell = sells.get(order.getBook());
                int val = order.getVolume();
                if (order.getOperation().equals("BUY")) {
                    if (setBuy.containsKey(order.getPrice())) {
                        val += setBuy.get(order.getPrice()).getVolume();
                    }
                        order.setVolume(val);
                    setBuy.put(order.getPrice(), order);
                }
                else {
                    if (setSell.containsKey(order.getPrice())) {
                        val += setSell.get(order.getPrice()).getVolume();
                    }
                    order.setVolume(val);
                    setSell.put(order.getPrice(), order);
                }
            }
        }
    }

    //Логика совпадений
    public void matching () {
        for (Map.Entry<String, TreeMap<Double, Order>> mapB : buys.entrySet()) {
            for (Map.Entry<String, TreeMap<Double, Order>> mapS : sells.entrySet()) {
                List<Order> listB = new ArrayList<>(mapB.getValue().values());
                List<Order> listS = new ArrayList<>(mapS.getValue().values());
                resultBuys.putIfAbsent(mapB.getKey(), new ArrayList<>());
                resultSells.putIfAbsent(mapS.getKey(), new ArrayList<>());
                for (int i = 0; i < listB.size(); i++) {
                    for (int j = 0; j < listS.size(); j++) {
                        Order ordB = listB.get(i);
                        Order ordS = listS.get(j);
                            if (ordB.getPrice() >= ordS.getPrice()) {
                                if (ordB.getVolume() > ordS.getVolume()) {
                                    ordB.setVolume(ordB.getVolume() - ordS.getVolume());
                                }
                                else {
                                    ordS.setVolume(ordS.getVolume() - ordB.getVolume());
                                    break;
                                }
                            }
                            else {
                                resultBuys.get(mapB.getKey()).add(ordB);
                                resultSells.get(mapS.getKey()).add(ordS);
                                j++;
                                break;
                            }
                        }
                }
            }
        }

    }

    public void getMap() {
        for (Map.Entry<String, ArrayList<Order>> map : resultBuys.entrySet()) {
            System.out.println(map.getKey());
            for (Order order : map.getValue()) {
                System.out.println(order);
            }
        }

        for (Map.Entry<String, ArrayList<Order>> map : resultSells.entrySet()) {
            System.out.println(map.getKey());
            for (Order order : map.getValue()) {
                System.out.println(order);
            }
        }
    }

}

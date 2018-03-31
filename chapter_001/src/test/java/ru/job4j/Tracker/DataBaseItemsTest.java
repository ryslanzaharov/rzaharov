package ru.job4j.Tracker;

import org.junit.Test;

import java.util.List;

/*
* Тест при соединении с бд.
*/
public class DataBaseItemsTest {

    @Test
    public void whenPerformTransactionsWithItemsInDatabase() {
        String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
        String username = "postgres";
        String password = "";
        DataBaseItems db = new DataBaseItems(url, username, password);
        Item item1 = new Item("Ivan");
        Item item2 = new Item("Andrey");
        Item item3 = new Item("Egor");
        Item item4 = new Item("Stepan");
        Item item5 = new Item("Aleksandr");
        //соеднияемся с бд.
        db.open();
        //добавляем заявки.
        db.insert(item1);
        db.insert(item2);
        db.insert(item3);
        db.insert(item4);
        //удаляем заявки по имени.
        db.deleteByName(item2);
        //удаляем заявки по  id.
        db.deleteById(1);
        //получаем заявку с помощью id.
        System.out.println(db.getItemById(3));
        //получаем заявку с помощью name.
        System.out.println(db.getItemByName("Stepan"));
        //изменяем имя с помощью id.
        db.updateItemNameById(5, "Oleg");
        //получаем список заявок.
        for (String item : db.getItems()) {
            System.out.println(item);
        }
        //закрываем соединение с бд.
        db.close();
    }


}
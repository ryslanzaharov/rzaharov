package ru.job4j.parsing;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Класс запуска парсера.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 15.04.18.
 */
public class StartParser{
    /**
    * адрес парируемого сайта.
    */
    private final static String URL = "http://www.pgsql.ru/forum/job-offers/";

    public static void main(String[] args) {
        ScannerParser jp = new ScannerParser(URL, new DBConnection("jdbc:postgresql://localhost:5432/java_a_from_z"));
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleWithFixedDelay(jp, 0, 1 , TimeUnit.DAYS);
    }
}

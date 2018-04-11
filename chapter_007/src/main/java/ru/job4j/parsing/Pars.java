package ru.job4j.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pars {

    private static String URL = "http://www.sql.ru/forum/job-offers/";
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        JobParser jp = new JobParser(URL, new DBConnection("jdbc:postgresql://localhost:5432/java_a_from_z"));
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleWithFixedDelay(jp, 0, 5, TimeUnit.SECONDS);


        long l2 = System.currentTimeMillis() - l1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l2);
        long sec = calendar.get(Calendar.SECOND);
        System.out.println(sec);
    }
}

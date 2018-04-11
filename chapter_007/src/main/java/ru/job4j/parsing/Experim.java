package ru.job4j.parsing;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Experim {
    public static void main(String[] args) {
        Date date = new Date();
        // Вывод текущей даты и времени с использованием toString()
        String str = String.format("%tc", date);
        System.out.println(str);

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd 'и время' hh:mm:ss a zzz");
        String strs = String.format("%tc", dateNow);
        System.out.println(formatForDateNow.format(dateNow));

        String months[] = {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен",
                "окт", "ноя", "дек"};

    }

}

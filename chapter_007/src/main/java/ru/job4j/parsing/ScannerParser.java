package ru.job4j.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс сканирующий сайт.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 18.08.17.
 */

public class ScannerParser implements Runnable {

    private String url;
    private DBConnection db;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy, hh:mm", myDateFormatSymbols );
    private SimpleDateFormat dd_mmmm_yy = new SimpleDateFormat("dd MMMM yy", myDateFormatSymbols);
    private Pattern p = Pattern.compile("[j,J]ava\\s?(?!\\s?[s,S]cript)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    public static boolean isFirstEl = true;

    private static final Logger log = LoggerFactory.getLogger(ScannerParser.class);
    private int i = 1;
    private boolean isInsert = true;

    public ScannerParser(String url, DBConnection db) {
        this.url = url;
        this.db = db;
    }

    @Override
    public void run() {
        i = 1;
        isFirstEl = true;
        isInsert = true;
        parser();

    }

    /**
     * Метод парсирует сайт.
     */

    public void parser() {
        try {
            while (isInsert) {
                Document document = Jsoup.connect(url + i).get();
                Elements urls = document.select(".forumTable tbody tr");
                for (Element url : urls) {
                    Matcher m = p.matcher(url.text());
                    if (m.find()) {
                        //автор и дата.
                        Elements dateAuth = url.select("td.altCol");
                        Date date = timeParser(dateAuth.last().text());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        //текущая дата.
                        Calendar now = Calendar.getInstance();
                        int year = now.get(Calendar.YEAR);
                        if (calendar.get(Calendar.YEAR) == year) {
                            isInsert = db.insert(url.select(".postslisttopic a").first().text(), dateAuth.first().text(), new Timestamp(date.getTime()),
                                    url.getElementsByTag("a").attr("href"), i);
                            if (!isInsert)
                                break;
                        } else {
                            isInsert = false;
                        }
                    }
                }
                i++;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Вывод даты объявления вакансии.
     */

    public Date timeParser(String dateVacancy) {
        Date date = null;
        try {
            if (dateVacancy.contains("сегодня"))
                dateVacancy = dateVacancy.replaceAll("сегодня", dd_mmmm_yy.format(new Date()));
            else if (dateVacancy.contains("вчера")) {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, -1);
                dateVacancy = dateVacancy.replaceAll("вчера", dd_mmmm_yy.format(c.getTime()));
            }
            //парсирование в Date.
            date = dateFormat.parse(dateVacancy);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){
        @Override
        public String[] getMonths() {
            return new String[]{"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен",
                    "окт", "ноя", "дек"};
        }

    };
}

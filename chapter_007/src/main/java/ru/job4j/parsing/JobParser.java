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

public class JobParser implements Runnable {

    private String url;

    private DBConnection db;
    private boolean thisYear = true;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy, hh:mm", myDateFormatSymbols );
    private SimpleDateFormat dd_mmmm_yy = new SimpleDateFormat("dd MMMM yy", myDateFormatSymbols);
    private Pattern p = Pattern.compile("(.*)(java[^script][^(\\s-)script])(.*)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private boolean isFirstStart = true;

    private static final Logger log = LoggerFactory.getLogger(JobParser.class);
    private int i = 1;

    public JobParser(String url, DBConnection db) {
        this.url = url;
        this.db = db;
    }

    @Override
    public void run() {
        db.open();
        if (isFirstStart) {
            while (thisYear) {
                parser();
            }
            isFirstStart = false;
        }
        parser();

    }

    public void parser() {
        try {
                Document document = Jsoup.connect(url+i).get();
                Elements urls = document.select(".forumTable tbody tr");
                for (Element url : urls) {
                    Matcher m = p.matcher(url.text());
                    if (m.find()) {
                        //автор и дата.
                        Elements dateAuth = url.select("td.altCol");
                        //дата объявдения вакансии.
                        String dateVacancy = dateAuth.last().text();
                        if (dateVacancy.contains("сегодня"))
                            dateVacancy = dateVacancy.replaceAll("сегодня", dd_mmmm_yy.format(new Date()));
                        else if (dateVacancy.contains("вчера"))
                            dateVacancy = dateVacancy.replaceAll("вчера", dd_mmmm_yy.format(new Date()));
                        //парсирование в Date.
                        Date date = dateFormat.parse(dateVacancy);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime (date);
                        //текущая дата.
                        Calendar now = Calendar.getInstance();
                        int year = now.get(Calendar.YEAR);
                        if (calendar.get(Calendar.YEAR) == year) {
                            db.insert(url.select(".postslisttopic a").first().text(), dateAuth.first().text(), new Timestamp(date.getTime()),
                                    url.getElementsByTag("a").attr("href"), i);
                        }
                        else thisYear = false;
                    }
                }
                i++;
         //   db.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен",
                    "окт", "ноя", "дек"};
        }

    };
}

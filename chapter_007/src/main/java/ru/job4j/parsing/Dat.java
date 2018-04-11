package ru.job4j.parsing;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dat {

    public static void main(String[] args) {
        Date currentDate = new Date();
        String dfs = new String("07 апр 18, 06:38");
        SimpleDateFormat dateFormats = new SimpleDateFormat("dd MMMM yy, hh:mm", myDateFormatSymbols );
        SimpleDateFormat dateFormats2 = new SimpleDateFormat("dd MMMM yy", myDateFormatSymbols );
        System.out.println(dateFormats.format( currentDate ) );
        Date d = new Date();
        System.out.println(dateFormats2.format(d));

        try {
            Date newDA = dateFormats.parse(dfs);
            System.out.println(newDA);
            Timestamp ts = new Timestamp(newDA.getTime());
            System.out.println(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        try{
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String df = new String("07 апр 18, 06:38");
//            Date formatedDate = dateFormat.parse(df);
//            java.sql.Date date = new java.sql.Date(formatedDate.getTime());
//            System.out.println(formatedDate);
//            System.out.println(date);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен",
                    "окт", "ноя", "дек"};
        }

    };
}

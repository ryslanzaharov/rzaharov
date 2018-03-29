package ru.job4j.sql.xmloptimization;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.util.Calendar;

public class SQLiteTest {

    @Test
    public void whenInsertDateInTable() {
        long l1 = System.currentTimeMillis();
        SQLite sqLite = new SQLite("jdbc:sqlite:C:\\sqlite\\TEST.FIELD", 1000000);
        sqLite.setXml1(new File("D:\\1.xml"));
        sqLite.setXml2(new File("D:\\2.xml"));
        sqLite.setXslFile(new File("D:\\xs.xsl"));
        sqLite.open();
        sqLite.insert();
        //  sqLite.createXmlDocByDom();
        //   sqLite.createXmlDocByJaxb();
        sqLite.createXmlDocumentByStax();
        sqLite.transformationXMLinXLST();
        sqLite.close();
        long totalTime = System.currentTimeMillis() - l1;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis( totalTime );
        long seconds = cal.get(Calendar.SECOND);
        System.out.println(seconds);
    }
}
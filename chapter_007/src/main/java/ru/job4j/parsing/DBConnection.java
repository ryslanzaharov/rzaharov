package ru.job4j.parsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Класс для соединения с бд и добавления данных.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 18.08.17.
 */

public class DBConnection{

    private Connection conn;
    private boolean isInsert = true;
    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);
    public static String isCopy;

    public DBConnection(String url) {
        try {
            conn = DriverManager.getConnection(url, "postgres", "");
            conn.setAutoCommit(false);
            conn.prepareStatement(SqlQuery.CREATE_TABLE).executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * добавление данных вакансии в бд.
     */

    public boolean insert(String vacancy, String author, Timestamp date, String links, int i) {
        Savepoint savepoint = null;
        try(final PreparedStatement ps = conn.prepareStatement(SqlQuery.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            savepoint = conn.setSavepoint(String.valueOf(i));
            if (revise(links)) {
                ps.setString(1, vacancy);
                ps.setString(2, author);
                ps.setTimestamp(3, date);
                ps.setString(4, links);
                ps.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            try {
                log.error(e.getMessage(), e);
                conn.rollback(savepoint);
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }
        }
        return isInsert;
    }

    /**
     * сверяем ссылки вакансии для исключения дублирования.
     */

    public boolean revise (String links) {
        try(final PreparedStatement result = conn.prepareStatement(SqlQuery.SELECT)) {
            if (ScannerParser.isFirstEl) {
                ScannerParser.isFirstEl = false;
                result.setMaxRows(1);
                ResultSet rs = result.executeQuery();
                if (rs.next())
                    isCopy = rs.getString("links");
            }
            if (isCopy != null && isCopy.equals(links))
                isInsert = false;
            else
                isInsert = true;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return isInsert;
    }
}

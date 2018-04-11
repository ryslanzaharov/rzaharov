package ru.job4j.parsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DBConnection{

    private final String url;

    private Connection conn;
    private boolean isInsert = true;
    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);

    public DBConnection(String url) {
        this.url = url;
    }

    public void open() {
        try {
            conn = DriverManager.getConnection(url, "postgres", "");
            if (conn == null) {
                System.exit(0);
            }
            conn.setAutoCommit(false);
            conn.prepareStatement(SqlQuery.CREATE_TABLE).executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void insert(String vacancy, String author, Timestamp date, String links, int i) {
        Savepoint savepoint = null;
        try {
            savepoint = conn.setSavepoint(String.valueOf(i));
            PreparedStatement ps = conn.prepareStatement(SqlQuery.INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            PreparedStatement result = conn.prepareStatement(SqlQuery.SELECT);
            result.setMaxRows(1);
            ResultSet rs = result.executeQuery();
            if (rs.next()) {
                if (rs.getString("vacancy").equals(vacancy) && rs.getString("author").equals(author)
                        && rs.getTimestamp("date").equals(date) && rs.getString("links").equals(links) ) {
                    isInsert = false;
                    System.out.println(rs.getString("date"));
                }
                else isInsert = true;
            }
            if (isInsert) {
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
                conn.rollback(savepoint);
            } catch (SQLException e1) {
            }
        }
    }

    public void close() {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}

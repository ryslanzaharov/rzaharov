package ru.job4j.Tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

/**
 * Класс для соединения и опреаций с бд.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 31.03.18.
 */

public class DataBaseItems {

    private Connection conn;

    private static Logger log = LoggerFactory.getLogger(DataBaseItems.class);

    private final String url;
    private final String username;
    private final String password;

    private String item;
    private boolean isUpdate;

    public DataBaseItems(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConn() {
        return conn;
    }

    public void open() {
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            if (conn == null)
                System.exit(0);
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS itemss(id SERIAL PRIMARY KEY, name TEXT, items_date TIMESTAMP)").executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            log.error(e.getMessage(), e);
        }
    }

    public void insert(Item item) {
        try(PreparedStatement pst = conn.prepareStatement("INSERT INTO itemss(name, items_date) VALUES(?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, item.getName());
            pst.setTimestamp(2, item.getDate());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

    public void deleteByName(Item item) {
        try(PreparedStatement pst = conn.prepareStatement("DELETE FROM itemss WHERE name IN (?)")) {
            pst.setString(1, item.getName());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        try(PreparedStatement pst = conn.prepareStatement("DELETE FROM itemss WHERE id IN (?)")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public String getItemById(int id) {
        try(PreparedStatement pst = conn.prepareStatement("SELECT * FROM itemss AS i WHERE i.id IN (?)")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                item = (String.format("%s %s", rs.getString("name"), rs.getTimestamp("items_date")));

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return item;
    }

    public ArrayList<String> getItemByName(String name) {
        ArrayList<String> list = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement("SELECT * FROM itemss AS i WHERE i.name IN (?)")) {

            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
                list.add(String.format("%s %s", rs.getString("name"), rs.getTimestamp("items_date")));

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public boolean updateItemNameById(int id, String name) {
        try(PreparedStatement pst = conn.prepareStatement("UPDATE itemss SET name = ? WHERE id = ?")) {
            pst.setString(1, name);
            pst.setInt(2, id);
            pst.executeUpdate();
            isUpdate = true;
        } catch (SQLException e) {
            isUpdate = false;
            log.error(e.getMessage(), e);
        }
        return isUpdate;
    }

    public ArrayList<String> getItems() {
        ArrayList<String> list = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement("SELECT * FROM itemss")) {
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(String.format("%s %s", rs.getString("name"), rs.getTimestamp("items_date")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }


}

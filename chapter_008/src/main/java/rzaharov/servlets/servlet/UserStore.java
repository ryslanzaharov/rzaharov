package rzaharov.servlets.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Класс для соединения с бд.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 02.05.18.
 */

public class UserStore {

    public enum UserStoreSingleton {
        INSTANCE;

        public UserStore getInstance() {
            if (users == null) {
                try {
                    users = new UserStore();
                }catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            return users;
        }
    }

    private static final Logger log = LoggerFactory.getLogger(UserStore.class);

    public static Connection conn;

    private String URL = "jdbc:postgresql://localhost:5432/java_a_from_z";

    private static volatile UserStore users;

    private UserStore() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, "postgres", "");
            conn.prepareStatement(SqlQuery.CREATE_TABLE).executeUpdate();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public boolean isCredentional(String email, String password) {
        boolean exists = false;
        if (!email.isEmpty() && !password.isEmpty()) {
            User user = select(email);
            if (user.getEmail() != null)
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                exists = true;
            }
        }
        return exists;
    }

    public int insert(User user) {
        int i = 0;
        try(PreparedStatement insert = conn.prepareStatement(SqlQuery.INSERT)) {
            insert.setString(1, user.getEmail());
            insert.setString(2, user.getName());
            insert.setString(3, user.getLogin());
            insert.setTimestamp(4, user.getCreateDate());
            insert.setString(5, user.getPassword());
            insert.setString(6, user.getRole());
            i = insert.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return i;
    }

    public List<User> select() {
        List<User> usersList = new ArrayList<>();
        try {
            PreparedStatement select = conn.prepareStatement(SqlQuery.SELECT);
            ResultSet rs = select.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setCreateDate(rs.getTimestamp("date"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return usersList;
    }

    public User select(String email) {
        User user = new User();
        try {
            PreparedStatement select = conn.prepareStatement(SqlQuery.SELECT_EMAIL);
            select.setString(1, email);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setCreateDate(rs.getTimestamp("date"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return user;
    }

    public int update(String email, User user) {
        int i = 0;
        try(final PreparedStatement update = conn.prepareStatement(SqlQuery.UPDATE)) {
            update.setString(1, user.getEmail());
            update.setString(2, user.getName());
            update.setString(3, user.getLogin());
            update.setTimestamp(4, user.getCreateDate());
            update.setString(5, user.getPassword());
            update.setString(6, user.getRole());
            update.setString(7, email);
            i = update.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return i;
    }

    public int delete(String email) {
        int i = 0;
        try(final PreparedStatement delete = conn.prepareStatement(SqlQuery.DELETE)) {
            delete.setString(1, email);
            i = delete.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return i;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}


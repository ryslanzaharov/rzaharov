package rzaharov.servlets.crudServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

/**
 * Класс для соединения с бд.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 22.04.18.
 */

public class UserStore {

    private static final Logger log = LoggerFactory.getLogger(UserStore.class);

    public static Connection conn;

    private String URL = "jdbc:postgresql://localhost:5432/java_a_from_z";

    private static final UserStore users = new UserStore();

    private UserStore() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, "postgres", "");
            System.out.println("constructor " + conn );
            conn.prepareStatement(SqlQuery.CREATE_TABLE).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

    public static UserStore getInstance() {
        return users;
    }

    public int insert(User user) {
        int i = 0;
        try(PreparedStatement insert = conn.prepareStatement(SqlQuery.INSERT)) {
            insert.setString(1, user.getEmail());
            insert.setString(2, user.getName());
            insert.setString(3, user.getLogin());
            insert.setTimestamp(4, user.getCreateDate());
            i = insert.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return i;
    }

    public User select(String email) {
        User user = new User();
        try {
            PreparedStatement select = conn.prepareStatement(SqlQuery.SELECT);
            select.setString(1, email);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setCreateDate(rs.getTimestamp("date"));
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
            update.setString(5, email);
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

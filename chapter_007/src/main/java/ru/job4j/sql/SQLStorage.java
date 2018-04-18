package ru.job4j.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SQLStorage {
    private static final Logger log = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
        String username = "postgres";
        String password = "";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("insert into users(login, password, create_date) values(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
      //      PreparedStatement st = conn.prepareStatement("update users set login = ? where id = ?");
            st.setString(1, "qqqq");
            st.setInt(2, 10);
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
//            int rowsDeleted = st.executeUpdate();
//            ResultSet generatedKeys = st.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                System.out.println(generatedKeys.getInt(1));
//            }
//            System.out.println(rowsDeleted + " rows deleted");
//            st.close();
//            PreparedStatement st = conn.prepareStatement("SELECT * FROM users as u where u.id in (?, ?, ?)");
//            st.setInt(1, 3);
//            st.setInt(2, 7);
//            st.setInt(3, 8);
//            ResultSet rs = st.executeQuery();
//            while (rs.next())
//            {
//                System.out.print("Column 1 returned ");
//                System.out.println(String.format("%s %s", rs.getString("login"), rs.getTimestamp("create_date")));
//            }
//            rs.close();
//            st.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
        }
    }
}

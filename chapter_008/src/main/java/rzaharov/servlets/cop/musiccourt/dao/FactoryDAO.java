package rzaharov.servlets.cop.musiccourt.dao;

import rzaharov.servlets.cop.musiccourt.dao.postgres.UserDatabaseDao;
import rzaharov.servlets.cop.musiccourt.models.User;

import java.sql.Connection;
import java.sql.DriverManager;

public class FactoryDAO {
    public static Connection conn;

    private String user = "postgres";//Логин пользователя
    private String password = "";//Пароль пользователя
    private String url = "jdbc:postgresql://localhost:5432/java_a_from_z";//URL адрес
    private String driver = "org.postgresql.Driver";//Имя драйвера

    private static volatile FactoryDAO instance;
    private static Object mutex = new Object();

    public static FactoryDAO getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null)
                    instance = new FactoryDAO();
            }
        }
        return instance;
    }

    private FactoryDAO() {
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            //  connection.prepareStatement()
        } catch (Exception e) {}
    }

    public boolean isCredentional(String login, String password) {
        boolean isCred = false;
        UserDatabaseDao udd = new UserDatabaseDao();
        if (udd.getUser(login).getLogin() != null && udd.getUser(login).getPassword().equals(password)) {
            isCred = true;
        }
        return isCred;
    }

    public Connection getConn() {
        return conn;
    }
}


package rzaharov.servlets.cop.musiccourt.dao.postgres;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryDAO {

    public enum Singleton{

        INSTANCE;

        public FactoryDAO getInstance() {
            if (instance == null) {
                synchronized (mutex) {
                    if (instance == null)
                        instance = new FactoryDAO();
                }
            }
            return instance;
        }
    }

    private static final Logger log = LoggerFactory.getLogger(FactoryDAO.class);
    private PGSimpleDataSource dataSource;

    private String user;
    private String password;
    private String url;

    private static volatile FactoryDAO instance;
    private static Object mutex = new Object();

    private FactoryDAO() {
        init();
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setUser(this.user);
        this.dataSource.setPassword(this.password);
        this.dataSource.setDatabaseName(this.url);
    }

    public void init() {
        try {

            Properties properties = new Properties();
            properties.load(FactoryDAO.class.getClassLoader().getResourceAsStream("db.properties"));
            this.user = properties.getProperty("DB_USER");
            this.password = properties.getProperty("DB_PASSWORD");
            this.url = properties.getProperty("DB_URL");
            Class.forName(properties.getProperty("DB_DRIVER"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
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
        Connection conn = null;
        try {
            conn = this.dataSource.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return conn;
    }
}


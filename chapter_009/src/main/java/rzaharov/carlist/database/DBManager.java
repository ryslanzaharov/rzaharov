package rzaharov.carlist.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBManager {

    private SessionFactory sessionFactory;

    private static final DBManager DB_MANAGER = new DBManager();

    private DBManager(){}

    public static DBManager getInstance() {
        return DB_MANAGER;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void buildSessionFactory() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void closeSessionFactory() {
        this.sessionFactory.close();
    }
}

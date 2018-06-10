package rzaharov.todolist.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sun.security.krb5.Config;

public class DBManager {

    private SessionFactory factory;

    private static final DBManager DB_MANAGER = new DBManager();

    private DBManager() {}

    public static DBManager getInstance() {
        return DB_MANAGER;
    }

    public Session getSession() {
        Session session = this.factory.openSession();
        return session;
    }

    public void buildSessionFactry() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public void closeSessionFactory() {
        this.factory.close();
    }
}

package rzaharov.example.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

/**
 * @author ryslan
 * @version 0.1
 * @since 06.06.2017
 *
 * Wrapper for work with database accross hibernate orm.
 */
public class DBManager {

    private SessionFactory factory;

    private static final DBManager DB_MANAGER = new DBManager();

    private DBManager(){}

    public static DBManager getInstance() {
        return DB_MANAGER;
    }

   public Session getSession() {

       Session session = factory.openSession();
       return session;
   }

    public void buildSessionFactory() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public void closeSessionFactory() {
        this.factory.close();
    }

}

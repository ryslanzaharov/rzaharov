package ru.rzaharov.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBManager {

    private SessionFactory sessionFactory;

    private static final DBManager DB_MANAGER = new DBManager();

    private static final StandardServiceRegistry REGISTRY =
            new StandardServiceRegistryBuilder().configure().build();

    private DBManager(){}

    public static DBManager getInstance() {
        return DB_MANAGER;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void buildSessionFactory() {
        this.sessionFactory = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();
                //new Configuration().configure().buildSessionFactory();
    }

    public void closeSessionFactory() {
        this.sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

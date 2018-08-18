package ru.rzaharov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportUser {

    private static final Logger log = LoggerFactory.getLogger(ImportUser.class);

    private SessionFactory sessionFactory;

    private static final ImportUser IMPORT_USER = new ImportUser();

    private static final StandardServiceRegistry REGISTRY =
            new StandardServiceRegistryBuilder().configure().build();

    public ImportUser() {
        buildSessionFactory();
    }

    public static ImportUser getInstance() {
        return IMPORT_USER;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void buildSessionFactory() {
        this.sessionFactory = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();
        //new Configuration().configure().buildSessionFactory();
    }

    public void addUser(User user) {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }

    }

    public void closeSessionFactory() {
        this.sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

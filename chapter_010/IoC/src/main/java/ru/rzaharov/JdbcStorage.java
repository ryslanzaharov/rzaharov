package ru.rzaharov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JdbcStorage implements Storage {

    private static final Logger log = LoggerFactory.getLogger(JdbcStorage.class);

    private SessionFactory sessionFactory;

    private static final ImportUser IMPORT_USER = new ImportUser();

    private static final StandardServiceRegistry REGISTRY =
            new StandardServiceRegistryBuilder().configure().build();

    public JdbcStorage() {
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
    }

    @Override
    public void addUser(User user) {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public User getById(int id) {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();
        User user = null;
        try {
            user = session.get(User.class, id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }
        return user;
    }

    @Override
    public void update(int id, User user) {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<User> getAll() {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();
        List<User> users = null;
        try {
            users = session.createQuery("from User ").list();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }
        return users;
    }

    @Override
    public void remove(User user) {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
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

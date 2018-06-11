package rzaharov.example.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rzaharov.example.database.AllModels;
import rzaharov.example.database.DBManager;
import rzaharov.example.database.DBOperation;
import rzaharov.example.database.ID;

import java.util.List;

/**
 * @author rzaharov
 * @version 0.1
 * @since 07.06.2018
 */

public abstract class CommonRepository<T> {

    private DBManager dbManager;

    private static final Logger log = LoggerFactory.getLogger(DBManager.class);

    /**
     * Default constructor.
     */
    public CommonRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public void execute(DBOperation db, T value) {
        final Session session = this.dbManager.getSession();
        final Transaction tx = session.beginTransaction();
        try {
            db.execute(session, value);
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            tx.commit();
            session.close();
        }
    }

    public T getById(ID operation) {
        final Session session = this.dbManager.getSession();
        final Transaction tx = session.beginTransaction();
        T value = null;
        try {
             value = (T) operation.getById(session);
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            tx.commit();
            session.close();
        }
        return value;
    }

    public List<T> getAll(AllModels allModels) {
        List<T> list = null;
        final Session session = this.dbManager.getSession();
        final Transaction tx = session.beginTransaction();
        try {
            list = allModels.getAll(session);
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            tx.commit();
            session.close();
        }
        return list;
    }

}

package rzaharov.example.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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

    private Session sessions = null;

    /**
     * Default constructor.
     */
    public CommonRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public void execute(DBOperation db, T value) {
        try(Session session = this.dbManager.getSession()) {
            sessions = session;
            session.beginTransaction();
            db.execute(session, value);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            sessions.getTransaction().rollback();
        }
    }

    public T getById(ID operation) {
        T value = null;
        try(Session session = this.dbManager.getSession()) {
            sessions = session;
            session.beginTransaction();
            value = (T) operation.getById(session);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            sessions.getTransaction().rollback();
        }
        return value;
    }

    public List<T> getAll(AllModels allModels) {
        List<T> list = null;
        try(Session session = this.dbManager.getSession()) {
            sessions = session;
            session.beginTransaction();
            list = allModels.getAll(session);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            sessions.getTransaction().rollback();
        }
        return list;
    }

}

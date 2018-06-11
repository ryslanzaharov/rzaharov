package rzaharov.todolist.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rzaharov.todolist.database.AllModels;
import rzaharov.todolist.database.DBManager;
import rzaharov.todolist.database.DBOperation;
import rzaharov.todolist.database.ID;

import java.util.List;

public abstract class CommonRepository<T> {

    private static final Logger log = LoggerFactory.getLogger(CommonRepository.class);

    private DBManager dbManager;

    private Session sessions;

    public CommonRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public void execute(DBOperation dbOperation, T value) {
        final Session session = this.dbManager.getSession();
        final Transaction tx = session.beginTransaction();
        try {
            dbOperation.execute(session, value);
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            tx.commit();
            session.close();
        }
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

    public T getById(ID idOperation) {
        final Session session = this.dbManager.getSession();
        final Transaction tx = session.beginTransaction();
        T value = null;
        try {
            value = (T) idOperation.getById(session);
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            tx.commit();
            session.close();
        }
        return value;
    }
}

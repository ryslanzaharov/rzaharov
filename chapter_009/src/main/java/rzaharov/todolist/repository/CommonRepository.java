package rzaharov.todolist.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
        try(Session session = this.dbManager.getSession()) {
            this.sessions = session;
            session.beginTransaction();
            dbOperation.execute(session, value);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            sessions.getTransaction().rollback();
        }
    }

    public List<T> getAll(AllModels allModels) {
        List<T> list = null;
        try(Session session = this.dbManager.getSession()) {
            this.sessions = session;
            session.beginTransaction();
            list = allModels.getAll(session);
            session.getTransaction();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            sessions.getTransaction().rollback();
        }
        return list;
    }

    public T getById(ID idOperation) {
        T value = null;
        try(Session session = this.dbManager.getSession()) {
            this.sessions = session;
            session.beginTransaction();
            value = (T) idOperation.getById(session);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            sessions.getTransaction().rollback();
        }
        return value;
    }
}

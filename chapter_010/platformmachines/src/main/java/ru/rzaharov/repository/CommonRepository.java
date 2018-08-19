package ru.rzaharov.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rzaharov.database.AllModels;
import ru.rzaharov.database.DBManager;
import ru.rzaharov.database.DBOperation;
import ru.rzaharov.database.ID;

import java.util.List;


public abstract class CommonRepository<T> {

    private DBManager dbManager;

    private static final Logger log = LoggerFactory.getLogger(CommonRepository.class);

    public CommonRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public void execute(DBOperation dbOperation, T value) {
        final Session session = dbManager.getSessionFactory().openSession();
        final Transaction trans = session.beginTransaction();
        try {
            dbOperation.execute(session, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            trans.rollback();
        } finally {
            trans.commit();
            session.close();
        }
    }

    public T getById(ID idOperation) {
        final Session session = dbManager.getSessionFactory().openSession();
        final Transaction trans = session.beginTransaction();
        T value = null;
        try {
            value = (T)idOperation.getById(session);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            trans.rollback();
        } finally {
            trans.commit();
            session.close();
        }
        return value;
    }

    public List<T> getAll(AllModels allModels) {
        final Session session = dbManager.getSessionFactory().openSession();
        final Transaction trans = session.beginTransaction();
        List<T> lists = null;
        try {
            lists = allModels.getAll(session);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            trans.rollback();
        } finally {
            session.close();
        }
        return lists;
    }


}

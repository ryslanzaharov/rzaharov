package rzaharov.example.repository;

import org.hibernate.Session;
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

    /**
     * Default constructor.
     */
    public CommonRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public void execute(DBOperation db, T value) {
        Session session = this.dbManager.getSession();
        session.beginTransaction();
        db.execute(session, value);
        session.getTransaction().commit();
        session.close();
    }

    public T getById(ID operation) {
        Session session = this.dbManager.getSession();
        session.beginTransaction();
        T value = (T) operation.getById(session);
        session.getTransaction().commit();
        session.close();
        return value;

    }

    public List<T> getAll(AllModels allModels) {
        Session session = this.dbManager.getSession();
        session.beginTransaction();
        List<T> list = allModels.getAll(session);
        session.getTransaction().commit();
        session.close();
        return list;
    }

}

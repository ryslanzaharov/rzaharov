package rzaharov.todolist.repository;

import org.hibernate.Session;
import rzaharov.todolist.database.AllModels;
import rzaharov.todolist.database.DBManager;
import rzaharov.todolist.database.DBOperation;
import rzaharov.todolist.database.ID;

import java.util.List;

public abstract class CommonRepository<T> {

    private DBManager dbManager;

    public CommonRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public void execute(DBOperation dbOperation, T value) {
        Session session = this.dbManager.getSession();
        session.beginTransaction();
        dbOperation.execute(session, value);
        session.getTransaction().commit();
        session.close();
    }

    public List<T> getAll(AllModels allModels) {
        Session session = this.dbManager.getSession();
        session.beginTransaction();
        List<T> list = allModels.getAll(session);
        session.getTransaction();
        session.close();
        return list;
    }

    public T getById(ID idOperation) {
        Session session = this.dbManager.getSession();
        session.beginTransaction();
        T value = (T)idOperation.getById(session);
        session.getTransaction().commit();
        session.close();
        return value;
    }
}

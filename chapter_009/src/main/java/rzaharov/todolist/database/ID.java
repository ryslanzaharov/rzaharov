package rzaharov.todolist.database;

import org.hibernate.Session;

public interface ID<T> {

    T getById(Session session);
}

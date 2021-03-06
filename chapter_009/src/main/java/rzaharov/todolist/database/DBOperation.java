package rzaharov.todolist.database;

import org.hibernate.Session;

public interface DBOperation<T> {

    void execute(Session session, T value);
}

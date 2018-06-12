package rzaharov.carlist.database;

import org.hibernate.Session;

public interface DBOperation<T> {

    void execute(Session session, T value);
}

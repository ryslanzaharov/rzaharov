package rzaharov.example.database;


import org.hibernate.Session;

@FunctionalInterface
public interface DBOperation<T> {

    void execute(Session session, T value);
}

package rzaharov.example.database;

import org.hibernate.Session;

@FunctionalInterface
public interface ID<T> {

    T getById(Session session);
}

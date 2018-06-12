package rzaharov.carlist.database;

import org.hibernate.Session;

public interface ID<T> {

    T getById(Session session);

}

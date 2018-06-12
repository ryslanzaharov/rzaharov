package rzaharov.carlist.database;

import org.hibernate.Session;

import java.util.List;

public interface AllModels<T> {

    List<T> getAll(Session session);
}

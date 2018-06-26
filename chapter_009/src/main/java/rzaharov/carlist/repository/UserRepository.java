package rzaharov.carlist.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.User;

import java.util.List;

public class UserRepository extends CommonRepository<User> {

    private DBManager dbManager;

    private static final UserRepository instance = new UserRepository();

    private  UserRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public void add(User user) {
        super.execute(Session::save, user);
    }

    public void update(User user) {
        super.execute(Session::update, user);
    }

    public void delete(User user) {
        super.execute(Session::delete, user);
    }

    public User getById(int id) {
        return super.getById(session -> session.get(User.class, id));
    }

    public List<User> getUserByLogin(String login) {
        return super.getAll(session -> {
            Query query = session.createQuery("from User where login=:login")
                    .setParameter("login", login);
            return query.list();
        });
    }

    public List<User> getAll() {
        return super.getAll(session -> session.createQuery("from User").list());
    }
}


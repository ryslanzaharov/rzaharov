package rzaharov.example.repository;


import org.hibernate.Session;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.User;

import java.util.List;

/**
 * @author rzaharov
 * @version 0.1
 * @since 07.06.2018
 */

public class UserRepository extends CommonRepository<User>{

    private static final UserRepository instance = new UserRepository();

    private DBManager dbManager;

    private UserRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public static UserRepository getInstance() {
        return instance;
    }

   public void add(User user) {
        super.execute(Session:: save, user);
   }

   public void update(User user) {
        super.execute(Session :: update, user);
   }

   public void delete(User user) {
        super.execute(Session::delete, user);
   }

   public User getById(Integer id) {
        return super.getById(session -> session.get(User.class, id));
   }

   public List<User> getAll() {
        return super.getAll(session -> session.createQuery("from User ").list());
   }
}

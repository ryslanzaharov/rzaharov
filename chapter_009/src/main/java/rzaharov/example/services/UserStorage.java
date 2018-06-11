package rzaharov.example.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.User;

import java.sql.Timestamp;
import java.util.List;

public class UserStorage {

    public static void main(String[] args) {
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
//        session.beginTransaction();
////        User user = new User();
////        user.setId(1);
////        session.delete(user);
////        user.setLogin("rys");
////        user.setCreated(new Timestamp(System.currentTimeMillis()));
////        session.update(user);
////        session.save(user);
//        List<User> users = session.createQuery("from User").list();
//        for (User user : users) {
//            System.out.println(user.getLogin());
//        }
//        session.getTransaction().commit();
//        session.close();
//        factory.close();

    }
}

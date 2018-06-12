package rzaharov.example.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.Comment;
import rzaharov.example.models.Item;
import rzaharov.example.models.User;

import java.sql.Timestamp;
import java.util.List;

public class UserStorage {

    public static void main(String[] args) {
        //transient
//        User user = new User();
//        user.setLogin("instance life cycle");

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
//
//        Item item = new Item();
//        item.setItemName("test");
//        item.setAuthor(new User(38));
//        item.setCreated(new Timestamp(System.currentTimeMillis()));
//        session.save(item);

        Item item = session.get(Item.class, 1);
        System.out.println(item);

//        Comment comment = new Comment();
//        comment.setCommentName("name");
//        comment.setCommentText("bla bla bla");
//        comment.setCreated(new Timestamp(System.currentTimeMillis()));
//        comment.setItem(new Item(1));
//        session.save(comment);

        session.getTransaction().commit();
        session.close();
        factory.close();
//        session.save(user);
//        //after save - persistent
//
////        session.evict(user); //отсоединение от бд
//        user.setLogin("update");
//        user.setPassword("TEST");
//        session.flush();
//        session.update(user);
////        user.setCreated(new Timestamp(System.currentTimeMillis()));
////        session.update(user);
////        session.save(user);
//        List<User> users = session.createQuery("from User").list();
//        for (User user : users) {
//            System.out.println(user.getLogin());
//        }


    }
}

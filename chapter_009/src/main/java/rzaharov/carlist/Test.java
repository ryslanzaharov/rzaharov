package rzaharov.carlist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.Car;
import rzaharov.carlist.models.Condition;
import rzaharov.carlist.models.Engine;
import rzaharov.carlist.models.User;
import rzaharov.carlist.repository.CarRepository;
import rzaharov.carlist.repository.ConditionRepository;
import rzaharov.carlist.repository.EngineRepository;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {

    public static void main(String[] args) {
//        DBManager.getInstance().buildSessionFactory();
//        List<Car> cars = CarRepository.getInstance().getAll();
//        for (Car car : cars) {
//            System.out.println(car);
//        }
//
//        DBManager.getInstance().closeSessionFactory();
//        Car car = CarRepository.getInstance().getById(7);
//        System.out.println(car);
//        DBManager.getInstance().closeSessionFactory();

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
//        Engine engine = new Engine();
//        engine.setType_engine("122");
//        engine.setName("122");
//        engine.setCondition("122");
//        session.save(engine);
//        Condition condition = new Condition();
//        condition.setCondition("122");
//        condition.setMileage(122);
//        condition.setYear(122);
//        session.save(condition);
//        Set<Car> cars = new TreeSet<>(Arrays.asList(
//
//        ))
//        User user = new User();
//        user.setId(23);
//        user.setCreated(new Timestamp(System.currentTimeMillis()));
//        user.setLogin("122111");
//        user.setPassword("1221111");
//        Car car = new Car();
//        car.setId(26);
//        car.setMark("1134");
//        car.setModel("113");
//        car.setBody_type("2");
//        car.setPrice(1);
//        car.setSale("1");
//        car.setUser(user);
//        Engine engine = new Engine(3,"fwef22222", "we", "e");
//        Condition condition = new Condition(7,"222222", 22, 22);
//        car.setEngine(engine);
//        car.setCondition(condition);
//        session.update(car);
//        session.getTransaction().commit();

        System.out.println(session.createQuery("from Car").list());

//        session.save(user);
//        session.saveOrUpdate(car);

//        List<Condition> conditions = session.createQuery("from Condition").list();
//        for (Condition condition : conditions) {
//            System.out.println(condition);
//        }
//        List<User> cars = session.createQuery("from User").list();
//        for (User car : cars) {
//            System.out.println(car);
//        }
        session.close();
        factory.close();
    }
}

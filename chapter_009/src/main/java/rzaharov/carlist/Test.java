package rzaharov.carlist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.Car;
import rzaharov.carlist.models.Condition;
import rzaharov.carlist.models.Engine;
import rzaharov.carlist.repository.CarRepository;
import rzaharov.carlist.repository.CommonRepository;

public class Test {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        Car car = session.get(Car.class, 1);
        System.out.println(car);
        CommonRepository commonRepository = new CarRepository();


        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}

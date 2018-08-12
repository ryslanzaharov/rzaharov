package rzaharov.carlist.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.Car;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CarRepository extends CommonRepository<Car> {

    private static final CarRepository instance = new CarRepository();
    private DBManager dbManager;

    private CarRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public static CarRepository getInstance() {
        return instance;
    }

    public void add(Car car) {
        super.execute(Session::save, car);
    }

    public void update(Car car) {
        super.execute(Session::update, car);
    }

    public void delete(Car car) {
        super.execute(Session::delete, car);
    }

    public Car getById(int id) {
        return super.getById(session -> session.get(Car.class, id));
    }

    public List<Car> getAll() {
        return super.getAll(session -> session.createQuery("from Car c left join fetch c.user left join fetch c.engine" +
                " left join fetch c.condition").list());
    }

    public List<Car> getCarByParam(Car car) {
        return super.getAll(session -> {
            Query query = session.createQuery("from Car where mark=:mark and body_type=:bt and engine=:engine")
                    .setParameter("mark", car.getMark())
                    .setParameter("bt", car.getBody_type())
                    .setParameter("engine", car.getEngine());
            return query.list();
        });
    }

    public List<Car> getByUserId(int user_id) {
        return super.getAll(session -> {
            Query query = session.createQuery("from Car where user_id=:user_id")
                    .setParameter("user_id", user_id);
            return query.list();
        });
    }

    public List<Car> getByMark(String mark, boolean today) {
        String form = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(form);

        return super.getAll(session ->  {
            Query query;
            if (today)
            query = session.createQuery("from Car c left join fetch c.user left join fetch c.engine " +
                    " left join fetch c.condition order by c.date desc");
            else
                query = session.createQuery("from Car c left join fetch c.user left join fetch c.engine " +
                        " left join fetch c.condition where c.mark=:mark")
                        .setParameter("mark", mark);
            return query.list();
        });
    }


}

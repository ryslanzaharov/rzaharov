package rzaharov.carlist.repository;

import org.hibernate.Session;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.Car;

import java.util.List;

public class CarRepository extends CommonRepository<Car> {

    public static final CarRepository instance = new CarRepository();
    private DBManager dbManager;

    public CarRepository() {
        this.dbManager = DBManager.getInstance();
    }

    private static CarRepository getInstance() {
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
        return super.getAll(session -> session.createQuery("from Car").list());
    }


}

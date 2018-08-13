package rzaharov.carlist.repository;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import rzaharov.carlist.database.DBManager;
import rzaharov.carlist.models.Car;
import rzaharov.carlist.models.User;
import static org.hamcrest.core.Is.is;


import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CarRepositoryTest {

    private final CarRepository cars = CarRepository.getInstance();

    @BeforeClass
    public static void setUp() {
        DBManager.getInstance().buildSessionFactory();
    }

    @Test
    public void whenAddNewCar() {
        Car car = new Car();
        User user = new User("user", "passw");
        cars.add(car);
        assertThat(car.getId() != 0, is(true));
    }

    @Test
    public void whenGetAllCars() {
        Car car = new Car();
        Car car1 = new Car();
        User user = new User("user", "passw");
        cars.add(car);
        cars.add(car1);
        assertThat(cars.getAll().size(), is(2));
    }

    @AfterClass
    public static void tearDown() {
        DBManager.getInstance().closeSessionFactory();
    }

}

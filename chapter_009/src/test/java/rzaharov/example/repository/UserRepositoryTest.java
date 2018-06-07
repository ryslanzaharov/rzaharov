package rzaharov.example.repository;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.User;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserRepositoryTest {

    @BeforeClass
    public static void setUp() {
        DBManager.getInstance().buildSessionFactory();
    }

    @Test
    public void whenAddNewUser() {
        UserRepository userRepository = UserRepository.getInstance();
        User user = new User();
        user.setLogin("name");
        user.setPassword("password");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userRepository.add(user);
        assertThat(user.getId() != 0, is(true));
    }

    @Test
    public void whenUpdateUserAndGetById() {
        UserRepository userRepository = UserRepository.getInstance();
        User user = new User();
        user.setLogin("log");
        userRepository.add(user);
        assertThat(user.getId() != 0, is(true));
        user.setLogin("updateLog");
        userRepository.update(user);
        assertThat(user.getLogin(), is(userRepository.getById(user.getId()).getLogin()));
    }

    @Test
    public void delete() {
        UserRepository userRepository = UserRepository.getInstance();
        User user = new User();
        user.setLogin("log");
        userRepository.add(user);
        assertThat(user.getId() != 0, is(true));
        userRepository.delete(user);
        assertThat(userRepository.getById(user.getId()), is(nullValue()));
    }

    @Test
    public void whenGetAllUsers() {
        List<User> users = UserRepository.getInstance().getAll();
        assertThat(users.size() > 0, is(true));
    }

    @AfterClass
    public static void tearDown() {
        DBManager.getInstance().closeSessionFactory();
    }

}
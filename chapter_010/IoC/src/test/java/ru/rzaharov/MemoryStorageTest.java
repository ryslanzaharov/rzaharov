package ru.rzaharov;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.rzaharov.MemoryStorage;
import ru.rzaharov.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemoryStorageTest {

    @Test
    public void whenAddAndRemoveUserToTheStoreByBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        User user = new User();
        memory.addUser(user);
        assertThat(memory.getSize(), is(1));
        memory.remove(user);
        assertThat(memory.getSize(), is(0));
    }

    @Test
    public void whenAddAndGetUserByIdToTheStoreByBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        User user = new User();
        memory.addUser(user);
        assertNotNull(memory.getById(0));
    }

    @Test
    public void whenAddAndUpdateUserToTheStoreByBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        User user = new User();
        user.setFirstName("Ivan");
        memory.addUser(user);
        assertThat(memory.getById(0).getFirstName(), is("Ivan"));
        user.setFirstName("Petr");
        memory.update(0, user);
        assertThat(memory.getById(0).getFirstName(), is("Petr"));
    }

    @Test
    public void whenAddAndGetAllUserToTheStoreByBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        User user = new User();
        User user1 = new User();
        user.setFirstName("Ivan");
        user1.setFirstName("Petr");
        List<User> users = Arrays.asList(
                user, user1
        );
        memory.addUser(user);
        memory.addUser(user1);
        List<User> users1 = memory.getAll();
        assertThat(users, is(users1));
    }

}
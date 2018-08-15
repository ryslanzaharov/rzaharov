package ru.rzaharov;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        memory.removeUser(user);
        assertThat(memory.getSize(), is(0));
    }

}
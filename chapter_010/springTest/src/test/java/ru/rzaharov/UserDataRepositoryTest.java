package ru.rzaharov;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserDataRepositoryTest {

    @Test
    public void whenAddUserDataByCrudRepos() {
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-data-context.xml");
        UserDataRepository dataRepository = context.getBean(UserDataRepository.class);
        User user = new User();
        dataRepository.save(user);
    }
}
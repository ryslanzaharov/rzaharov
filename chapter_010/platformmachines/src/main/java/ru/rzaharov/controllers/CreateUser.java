package ru.rzaharov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rzaharov.crudrepository.CarDataRepository;
import ru.rzaharov.crudrepository.UserDataRepository;
import ru.rzaharov.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class CreateUser extends HttpServlet {

    private final UserDataRepository userDataRepository;

    @Autowired
    public CreateUser(final UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public String showCreatePage(){
        return "CreateUser";
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public void addUser(@ModelAttribute User user) {
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userDataRepository.save(user);
    }
}

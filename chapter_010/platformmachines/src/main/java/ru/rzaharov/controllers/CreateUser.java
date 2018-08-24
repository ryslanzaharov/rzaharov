package ru.rzaharov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rzaharov.models.User;
import ru.rzaharov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class CreateUser extends HttpServlet {

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public String showCreatePage(){
        return "CreateUser";
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public void addUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        UserRepository.getInstance().add(user);
    }
}

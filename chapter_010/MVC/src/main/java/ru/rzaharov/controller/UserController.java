package ru.rzaharov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rzaharov.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
//@RequestMapping(value = "/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    final List<User> users = new CopyOnWriteArrayList<>();

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        System.out.println("feef");
        model.addAttribute("users", this.users);
        return "user";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addItem(@ModelAttribute User user) {
        this.users.add(user);
        return "redirect:users.do";
    }
}

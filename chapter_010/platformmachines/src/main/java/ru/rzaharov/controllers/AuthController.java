package ru.rzaharov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.rzaharov.crudrepository.CarDataRepository;
import ru.rzaharov.crudrepository.UserDataRepository;
import ru.rzaharov.models.Car;
import ru.rzaharov.models.User;
import ru.rzaharov.repository.CarRepository;
import ru.rzaharov.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes("login")
public class AuthController {

    private final UserDataRepository userDataRepository;
    private final CarDataRepository carDataRepository;

    @Autowired
    public AuthController(final UserDataRepository userDataRepository, final CarDataRepository carDataRepository) {
        this.userDataRepository = userDataRepository;
        this.carDataRepository = carDataRepository;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String showSignInPage() throws ServletException, IOException {
        return "LoginView";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    protected String signIn(@RequestParam("login") String login, @RequestParam("password") String password,
                            ModelMap model, HttpSession session) {
        List<User> users = userDataRepository.getAll();
        boolean isCredentional = false;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                isCredentional = true;
                break;
            }
        }
        String path;
        if (isCredentional) {
            ModelAndView modelAndView = new ModelAndView();
            synchronized (modelAndView) {
                modelAndView.addObject("login", login);
                int user_id = userDataRepository.getUserByLogin(login).get(0).getId();
                List<Car> cars = carDataRepository.getByUserId(user_id);
                model.addAttribute("cars", cars);
                session.setAttribute("login", login);
            }
            path = "UpdateCar";
        } else {
            model.addAttribute("error", "Credentional invalid");
            path = "LoginView";
        }
        return path;
    }

    @RequestMapping(name = "/signout", method = RequestMethod.GET)
    public void showSignOutPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException   {
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public void signOut(HttpServletRequest req, HttpServletResponse resp, SessionStatus sessionStatus) throws ServletException, IOException {
        sessionStatus.setComplete();
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }
}

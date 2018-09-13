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

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        User user = userDataRepository.getUserByLogin(login).orElseThrow(() -> new EntityNotFoundException(login));
        boolean isCredentional = false;
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                isCredentional = true;
            }
        String path;
        if (isCredentional) {
            ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("login", login);
                List<Car> cars = carDataRepository.getByUserId(user.getId());
                model.addAttribute("cars", cars);
                session.setAttribute("login", login);
            path = "UpdateCar";
        } else {
            model.addAttribute("error", "Credentional invalid");
            path = "LoginView";
        }
        return path;
    }

    @RequestMapping(name = "/signout", method = RequestMethod.GET)
    public String showSignOutPage() throws ServletException, IOException   {
        return "index";
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public String signOut(SessionStatus sessionStatus) throws ServletException, IOException {
        sessionStatus.setComplete();
        return "index";
    }
}

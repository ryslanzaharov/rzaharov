package ru.rzaharov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.rzaharov.crudrepository.CarDataRepository;
import ru.rzaharov.crudrepository.UserDataRepository;
import ru.rzaharov.models.Car;
import ru.rzaharov.models.Condition;
import ru.rzaharov.models.Engine;
import ru.rzaharov.models.User;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@SessionAttributes(value = "login")
public class Edit {

    private final UserDataRepository userDataRepository;
    private final CarDataRepository carDataRepository;

    @Autowired
    public Edit(final UserDataRepository userDataRepository, final CarDataRepository carDataRepository) {
        this.userDataRepository = userDataRepository;
        this.carDataRepository = carDataRepository;
    }

    @RequestMapping(value = "/editCar", method = RequestMethod.GET)
    public String showYourAds(ModelMap model) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("login", auth.getName());
        List<Car> cars = carDataRepository.getByUserId(user.getId());
        model.addAttribute("cars", cars);
        return auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")) ? "UpdateCar" : "redirect:/login.do";
    }

    @RequestMapping(value = "/editCar", method = RequestMethod.POST)
    public String updateCar(@ModelAttribute Car car, @ModelAttribute Condition condition, @ModelAttribute Engine engine) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        car.setUser(user);
        car.setEngine(engine);
        car.setCondition(condition);
        car.setDate(new Timestamp(System.currentTimeMillis()));
        carDataRepository.save(car);
        return "UpdateCar";
    }
}

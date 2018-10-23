package com.platforms.controllers;

import com.platforms.crudrepository.CarDataRepository;
import com.platforms.crudrepository.UserDataRepository;
import com.platforms.models.Car;
import com.platforms.models.Condition;
import com.platforms.models.Engine;
import com.platforms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
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

package ru.rzaharov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rzaharov.crudrepository.CarDataRepository;
import ru.rzaharov.crudrepository.UserDataRepository;
import ru.rzaharov.models.Car;
import ru.rzaharov.models.Condition;
import ru.rzaharov.models.Engine;
import ru.rzaharov.models.User;
import ru.rzaharov.repository.CarRepository;
import ru.rzaharov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public String showYourAds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = userDataRepository.getUserByLogin(req.getParameter("login")).get(0).getId();
        List<Car> cars = carDataRepository.getByUserId(user_id);
        req.setAttribute("cars", cars);
        return "UpdateCar";
    }

    @RequestMapping(value = "/editCar", method = RequestMethod.POST)
    public String updateCar(@ModelAttribute("login") String login, HttpServletRequest req) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Car car = new Car();
        car.setId(Integer.parseInt(req.getParameter("car_id")));
        car.setMark(req.getParameter("mark"));
        car.setModel(req.getParameter("model"));
        car.setBody_type(req.getParameter("body_type"));
        car.setPrice(Integer.parseInt(req.getParameter("price")));
        car.setSale(req.getParameter("sale"));
        car.setEngine(new Engine(Integer.parseInt(req.getParameter("engId")),
                req.getParameter("engine_name"),
                req.getParameter("type_engine"),
                req.getParameter("engine_condition")));
        car.setCondition(new Condition(Integer.parseInt(req.getParameter("condId")),
                req.getParameter("condition_condition"),
                Integer.parseInt(req.getParameter("year")),
                Integer.parseInt(req.getParameter("mileage"))));
        User user = userDataRepository.getUserByLogin(session.getAttribute("login").toString()).get(0);
        car.setUser(user);
        carDataRepository.save(car);
        return "UpdateCar";
    }
}

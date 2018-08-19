package ru.rzaharov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class Edit {

    @RequestMapping(value = "/editCar", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int user_id = UserRepository.getInstance().getUserByLogin(session.getAttribute("login").toString()).get(0).getId();
        List<Car> cars = CarRepository.getInstance().getByUserId(user_id);
        System.out.println("1212" + cars);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/carlist/UpdateCar.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/editCar", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        Car car = new Car();
        car.setId(Integer.parseInt(req.getParameter("car_id")));
        car.setMark(req.getParameter("mark"));
        car.setModel(req.getParameter("model"));
        car.setBody_type(req.getParameter(req.getParameter("body_type")));
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
        User user = UserRepository.getInstance().getUserByLogin(session.getAttribute("login").toString()).get(0);
        car.setUser(user);
        CarRepository.getInstance().update(car);
        doGet(req, resp);
    }
}

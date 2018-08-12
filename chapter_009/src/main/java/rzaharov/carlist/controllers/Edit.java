package rzaharov.carlist.controllers;

import rzaharov.carlist.models.Car;
import rzaharov.carlist.models.Condition;
import rzaharov.carlist.models.Engine;
import rzaharov.carlist.models.User;
import rzaharov.carlist.repository.CarRepository;
import rzaharov.carlist.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int user_id = UserRepository.getInstance().getUserByLogin(session.getAttribute("login").toString()).get(0).getId();
        List<Car> cars = CarRepository.getInstance().getByUserId(user_id);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/carlist/UpdateCar.jsp").forward(req, resp);
    }

    @Override
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

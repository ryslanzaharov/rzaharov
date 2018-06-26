package rzaharov.carlist.controllers;

import rzaharov.carlist.models.User;
import rzaharov.carlist.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/carlist/CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String login  = req.getParameter("login");
        String password = req.getParameter("Password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        UserRepository.getInstance().add(user);
    }
}

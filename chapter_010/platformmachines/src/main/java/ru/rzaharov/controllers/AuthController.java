package ru.rzaharov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rzaharov.models.User;
import ru.rzaharov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class AuthController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String showSignInPage(ModelMap model) throws ServletException, IOException {
        return "LoginView";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    protected void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        List<User> users = UserRepository.getInstance().getAll();
        boolean isCredentional = false;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                isCredentional = true;
                break;
            }
        }
        if (isCredentional) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
            }
            resp.sendRedirect(String.format("%s/editCar", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentional invalid");
        }
    }

    @RequestMapping(name = "/signout", method = RequestMethod.GET)
    public void showSignOutPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException   {
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("login", null);
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }
}

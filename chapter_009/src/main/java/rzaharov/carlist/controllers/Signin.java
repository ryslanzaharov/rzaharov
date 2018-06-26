package rzaharov.carlist.controllers;

import rzaharov.carlist.models.User;
import rzaharov.carlist.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Signin extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/WEB-INF/carlist/LoginView.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
//                req.setAttribute();
 //               req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
                resp.sendRedirect(String.format("%s/editCar", req.getContextPath()));
            } else {
                req.setAttribute("error", "Credentional invalid");
                doGet(req, resp);
            }
        }
    }


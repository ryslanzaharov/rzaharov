package rzaharov.servlets.servlet.methods;

import rzaharov.servlets.servlet.User;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Сервлет для ввода данных пользователя.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 02.05.18.
 */

public class CreateController extends HttpServlet {

    private final UserStore users = UserStore.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("login") != null) {
            String role = users.select(req.getSession().getAttribute("login").toString()).getRole();
            if (role.equals("Admin"))
                req.setAttribute("role", role);
        }
        else
            req.setAttribute("role", "User");
        req.getRequestDispatcher("/WEB-INF/views/servletjsp/CreateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.user = new User();
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String role = req.getParameter("role");
        if (!email.equals("") && !name.equals("") && !login.equals("") && !password.equals("")
                && !country.equals("") && !city.equals("") && !role.equals("")) {
            user.setEmail(email);
            user.setName(name);
            user.setLogin(login);
            user.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            user.setPassword(password);
            user.setCountry(country);
            user.setCity(city);
            user.setRole(role);
            users.insert(user);
            doGet(req, resp);
        }
        else {
            req.setAttribute("error", "Please correct input date.");
            doGet(req, resp);
        }

    }
}

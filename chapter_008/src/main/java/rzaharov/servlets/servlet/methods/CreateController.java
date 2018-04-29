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
 * @since 29.04.18.
 */

public class CreateController extends HttpServlet {

    private final UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/servletjsp/CreateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.user = new User();
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        users.insert(user);
        resp.sendRedirect(String.format("%s/create", req.getContextPath()));
    }
}

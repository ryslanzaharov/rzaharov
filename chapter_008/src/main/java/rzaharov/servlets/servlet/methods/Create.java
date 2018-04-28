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
 * @since 22.04.18.
 */

public class Create extends HttpServlet {

    private final UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder();
        sb.append(
                "<form action='"+req.getContextPath()+"/create' method='post'>" +
                        "Email : <input type='text' name='email'>" +
                        "Name : <input type='text' name='name'>" +
                        "Login : <input type='text' name='login'>" +
                        "<input type='submit' value='Create'>" +
                        "</form>"
        );

        pw.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Create</title>" +
                "</head>" +
                "<body>" +
                "Edit users data :" +
                sb.toString() +
                "<form action='"+req.getContextPath()+"/list' method='get'>" +
                "<input type='submit' value='Back'>" +
                "</form>" +
                "</body>" +
                "</html>");
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i = 0;
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        this.user = new User();
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        if (!req.getParameter("email").equals("") && !req.getParameter("name").equals("") && !req.getParameter("login").equals(""))
        i = users.insert(user);
        if (i > 0) {
            doGet(req, resp);
            pw.append("user data added");
        }
        else
            pw.append("no user data added");
        pw.flush();
    }
}

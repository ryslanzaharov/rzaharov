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
 * Сервлет для изменения данных пользователя.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 22.04.18.
 */

public class Edit extends HttpServlet{

   private final UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        this.user = users.select(req.getParameter("email"));
        StringBuilder sb = new StringBuilder();
            sb.append(
                    "<form action='"+req.getContextPath()+"/edit' method='post'>" +
                            "<input type='hidden' name='oldEmail' value=" + user.getEmail() + ">" +
                            "Email : <input type='text' name='email' value=" +  user.getEmail() + ">" +
                            "Name : <input type='text' name='name' value=" + user.getName() + ">" +
                            "Login : <input type='text' name='login' value=" + user.getLogin() + ">" +
                            "<input type='submit' value='Edit'>" +
                            "</form>"
            );

        pw.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Edit</title>" +
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
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        this.user = new User();
        String oldEmail = req.getParameter("oldEmail");
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        users.update(oldEmail, user);
        int i = users.update(req.getParameter("email"), user);
        if (i > 0) {
            doGet(req, resp);
            pw.append("user data updated");
        }
        else
            pw.append("no user data updated");
        pw.flush();
    }
}

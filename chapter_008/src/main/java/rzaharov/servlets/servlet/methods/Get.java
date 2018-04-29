package rzaharov.servlets.servlet.methods;

import rzaharov.servlets.servlet.User;
import rzaharov.servlets.servlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервдет для получения данных с бд.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 22.04.18.
 */

public class Get extends HttpServlet {

  private final UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter pw = new PrintWriter(resp.getOutputStream());
//        List<User> usersList = users.select();
//        StringBuilder sb = new StringBuilder();
//        for (User user : usersList) {
//            sb.append(
//                    "<tr><td>" +  user.getEmail() + "</td>" +
//                    "<td>" + user.getName() + "</td>" +
//                    "<td>" + user.getLogin() + "</td>" +
//                    "<td>" + user.getCreateDate() + "</td>" +
//                    "<td><form action='" + req.getContextPath() + "/edit'>" +
//                    "<input type='hidden' name='email' value=" + user.getEmail() + " />" +
//                    "<button type='submit'>Update</button>" +
//                    "</form></td>" +
//                    "<td><form action='"+ req.getContextPath() + "/delete'>" +
//                    "<input type='hidden' name='email' value=" + user.getEmail() + " />" +
//                    "<button type='submit'>Delete</button>" +
//                    "</form></td></tr>"
//                        );
//        }
//        pw.append("<!DOCTYPE html>" +
//                "<html lang=\"en\">" +
//                "<head>" +
//                "    <meta charset=\"UTF-8\">" +
//                "    <title>List</title>" +
//                "</head>" +
//                "<body>" +
//                        "<table border=\"1\"><caption>Users data</caption>" +
//                        "    <tr>" +
//                        "    <th>Email</th>" +
//                        "    <th>Name</th>" +
//                        "    <th>Login</th>" +
//                        "    <th>Date</th>" +
//                        "    <th>Edit</th>" +
//                        "    <th>Delete</th>" +
//                        "   </tr>" +
//                            sb.toString() +
//                        "</table>" +
//                "<form action='"+req.getContextPath()+"/create' method='get'>" +
//                "<button type='submit'>Create</button>" +
//                "</form>" +
//                "</body>" +
//                "</html>");
//        pw.flush();
//    }
}

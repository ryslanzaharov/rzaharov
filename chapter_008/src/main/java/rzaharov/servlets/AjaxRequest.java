package rzaharov.servlets;

import org.json.JSONArray;
import rzaharov.servlets.servlet.User;
import rzaharov.servlets.servlet.UserStore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxRequest extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserStore users = UserStore.getInstance();

    public AjaxRequest() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        JSONArray arrayObj=new JSONArray();

        List<User> list = users.select();

        String query = request.getParameter("term");
        query = query.toLowerCase();
        for(int i=0; i<list.size(); i++) {
            String city = list.get(i).getCity().toLowerCase();
            if(city.startsWith(query)) {
                arrayObj.put(city);
            }
        }

        out.println(arrayObj.toString());
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Do something
    }

}

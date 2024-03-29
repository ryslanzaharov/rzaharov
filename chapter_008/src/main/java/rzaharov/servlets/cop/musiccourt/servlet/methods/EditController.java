package rzaharov.servlets.cop.musiccourt.servlet.methods;

import rzaharov.servlets.cop.musiccourt.dao.postgres.UserDatabaseDao;
import rzaharov.servlets.cop.musiccourt.models.Address;
import rzaharov.servlets.cop.musiccourt.models.MusicType;
import rzaharov.servlets.cop.musiccourt.models.Role;
import rzaharov.servlets.cop.musiccourt.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет для изменения данных пользователя.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 29.04.18.
 */

public class EditController extends HttpServlet{

    private final UserDatabaseDao udd = new UserDatabaseDao();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", udd.getUser(req.getParameter("login")));
        String login = req.getParameter("login");
        String role = udd.getRole(req.getSession().getAttribute("login").toString()).getName();
        if (login.equals(req.getSession().getAttribute("login")) ||
                role.equals("Admin")) {
            req.setAttribute("role", role);
            req.getRequestDispatcher("/WEB-INF/views/musiccourt/EditUser.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "No access rights!");
            req.getRequestDispatcher("/WEB-INF/views/musiccourt/").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.user = new rzaharov.servlets.cop.musiccourt.models.User();
        String oldLogin = req.getParameter("oldLogin");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String roleName = req.getParameter("role");
        String[] musicType = req.getParameterValues("musictype");
        List<MusicType> musicTypes = new ArrayList<>();
        for (String musName : musicType) {
            MusicType nameMusic = new MusicType();
            nameMusic.setId(Integer.parseInt(musName));
            nameMusic.setName(musName);
            musicTypes.add(nameMusic);
        }
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String street = req.getParameter("street");
        String house = req.getParameter("house");
        String apartment = req.getParameter("apartment");
        if (!name.equals("") && !login.equals("") && !password.equals("")
                && !surname.equals("") && !city.equals("") && !roleName.equals("")
                && !musicType.equals("") && !district.equals("") && !street.equals("")
                && !house.equals("") && !apartment.equals("")) {
            System.out.println(udd.getUser(login).getId());
            user.setId(udd.getUser(oldLogin).getId());
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setMusicTypes(musicTypes);
            Role role = new Role();
            role.setName(roleName);
            Address address = new Address();
            address.setCity(city);
            address.setDistrict(district);
            address.setStreet(street);
            address.setHouse(house);
            address.setApartment(Integer.parseInt(apartment));
            user.setRole_id(Integer.parseInt(roleName));
            user.setRole(role);
            user.setMusicTypes(musicTypes);
            user.setAddress(address);
            udd.update(user);
            doGet(req, resp);
        }
        else {
            req.setAttribute("error", "Please correct input date.");
            doGet(req, resp);
        }
    }
}

package rzaharov.servlets.cop.musiccourt.servlet.methods;

import rzaharov.servlets.cop.musiccourt.dao.FactoryDAO;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Сервлет для ввода данных пользователя.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 02.05.18.
 */

public class CreateController extends HttpServlet {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();

    private final UserDatabaseDao udd = new UserDatabaseDao();

    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("login") != null) {
            String role = udd.getRole(req.getSession().getAttribute("login").toString()).getName();
            if (role.equals("Admin"))
                req.setAttribute("role", role);
        }
        else
            req.setAttribute("role", "User");
        req.getRequestDispatcher("/WEB-INF/views/musiccourt/CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.user = new User();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String roleName = req.getParameter("role");
        String[] musicType = req.getParameterValues("musictype");
        List<Integer> musicTypes = new ArrayList<>();
        for (String musName : musicType) {
            MusicType nameMusic = new MusicType();
            nameMusic.setName(musName);
            musicTypes.add(Integer.parseInt(musName));
        }
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String street = req.getParameter("street");
        String house = req.getParameter("house");
        String apartment = req.getParameter("apartment");
        if (!name.equals("") && !login.equals("") && !password.equals("")
                && !surname.equals("") && !city.equals("") && !roleName.equals("")
                && !musicType.equals("") && !district.equals("") && !street.equals("")
                && !house.equals("") && apartment != null) {
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole_id(Integer.parseInt(roleName));
            Role role = new Role();
            role.setId(Integer.parseInt(roleName));
            role.setName(roleName);
            Address address = new Address();
            address.setCity(city);
            address.setDistrict(district);
            address.setStreet(street);
            address.setHouse(house);
            address.setApartment(Integer.parseInt(apartment));
            user.setRole(role);
            user.setAddress(address);
            udd.addUser(user, address, musicTypes, role);
            doGet(req, resp);
        }
        else {
            req.setAttribute("error", "Please correct input date.");
            doGet(req, resp);
        }

    }
}

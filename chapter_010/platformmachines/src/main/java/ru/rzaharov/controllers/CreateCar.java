package ru.rzaharov.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.rzaharov.crudrepository.CarDataRepository;
import ru.rzaharov.crudrepository.UserDataRepository;
import ru.rzaharov.models.Car;
import ru.rzaharov.models.Condition;
import ru.rzaharov.models.Engine;
import ru.rzaharov.models.User;
import ru.rzaharov.repository.CarRepository;
import ru.rzaharov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Controller
//@SessionAttributes(value = "login")
public class CreateCar{

    private static final Logger Log = LoggerFactory.getLogger(CreateCar.class);
    static final int fileMaxSize = 1000 * 1024;
    static final int memMaxSize = 1000 * 1024;

    private static final ConcurrentMap<String, String> FORM = new ConcurrentHashMap<String, String>();

    private String filePath ;
    private File file;
    private String fileName;

    private final CarDataRepository carDataRepository;

    @Autowired
    public CreateCar(final CarDataRepository carDataRepository) {
        this.carDataRepository = carDataRepository;
    }

    @RequestMapping(value = "/createCar", method = RequestMethod.GET)
    protected String showPage(@ModelAttribute("login") String login) {
        return "CreateCar";
    }

    @RequestMapping(value = "/createCar", method = RequestMethod.POST)
    public String addCar(HttpServletRequest req, @RequestParam("photo") String photo, @RequestParam("mark") String mark,
                         @RequestParam("model") String model, @RequestParam("body_type") String body_type,
                         @RequestParam("engine_name") String engine_name, @RequestParam("type_engine") String type_engine,
                         @RequestParam("engine_condition") String engine_condition, @RequestParam("condition_condition") String condition_condition,
                         @RequestParam("year") Integer year, @RequestParam("mileage") Integer mileage,
                         @RequestParam("price") Integer price, @RequestParam("sale") String sale) {
        try {
            Properties properties = new Properties();
            properties.load(CreateCar.class.getClassLoader().getResourceAsStream("fp.properties"));
            this.filePath = properties.getProperty("FILE_PATH");

//управление параметрами запроса.
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);
        //процесс парсинга.
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);
            //парсинг запроса.
            List<FileItem> fileItems = upload.parseRequest(req);
            //список запросов.
            //Iterator iterator = fileItems.iterator();
            //получаем сведения о файле.
            for (FileItem fileItem : fileItems) {
               // FileItem fileItem = (FileItem) iterator.next();
                //если это не обычное поле.
                if (!fileItem.isFormField()) {
                    //получаем название файла.
                    fileName = fileItem.getName();
                    //создаем файл.
                    file = new File(filePath + photo);
                    //записываем полученные данные в file на диске.
                    fileItem.write(file);
                }
            }
        HttpSession session = req.getSession();
        Car car = new Car();
        car.setMark(mark);
        car.setModel(model);
        car.setBody_type(body_type);
        car.setPrice(price);
        car.setSale(sale);
        car.setEngine(new Engine(
                engine_name,
                type_engine,
                engine_condition));
        car.setCondition(new Condition(
                condition_condition,
                year,
                mileage));
        if (session.getAttribute("login") != null) {
            User user = UserRepository.getInstance().getUserByLogin(session.getAttribute("login").toString()).get(0);
            car.setUser(user);
        }
        car.setPhoto("img/" + photo);
        carDataRepository.save(car);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return "CreateCar";
    }
}
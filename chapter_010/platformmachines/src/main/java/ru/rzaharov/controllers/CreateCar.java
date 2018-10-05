package ru.rzaharov.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
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
import org.springframework.web.multipart.MultipartFile;
import ru.rzaharov.crudrepository.CarDataRepository;
import ru.rzaharov.crudrepository.UserDataRepository;
import ru.rzaharov.models.Car;
import ru.rzaharov.models.Condition;
import ru.rzaharov.models.Engine;
import ru.rzaharov.models.User;
import ru.rzaharov.repository.CarRepository;
import ru.rzaharov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@MultipartConfig
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
    public String addCar(
            @ModelAttribute Car cars, @ModelAttribute Condition condition, @ModelAttribute Engine engine,HttpServletRequest req, HttpSession session
    ) {

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
                    file = new File(filePath + cars.getPhoto());
                    //записываем полученные данные в file на диске.
                    fileItem.write(file);
                }
            }
            User user = UserRepository.getInstance().getUserByLogin(session.getAttribute("login").toString()).get(0);
            cars.setUser(user);
            cars.setEngine(engine);
            cars.setCondition(condition);
        cars.setPhoto("img/" + cars.getPhoto());
        carDataRepository.save(cars);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return "CreateCar";
    }
}
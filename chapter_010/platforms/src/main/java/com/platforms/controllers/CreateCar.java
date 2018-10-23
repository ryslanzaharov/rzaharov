package com.platforms.controllers;

import com.platforms.crudrepository.CarDataRepository;
import com.platforms.crudrepository.UserDataRepository;
import com.platforms.models.Car;
import com.platforms.models.Condition;
import com.platforms.models.Engine;
import com.platforms.models.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
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
    private final UserDataRepository userDataRepository;

    @Autowired
    public CreateCar(final CarDataRepository carDataRepository,
                     final UserDataRepository userDataRepository) {
        this.carDataRepository = carDataRepository;
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "/createCar", method = RequestMethod.GET)
    protected String showPage(@ModelAttribute("login") String login) {
        return "CreateCar";
    }

    @RequestMapping(value = "/createCar", method = RequestMethod.POST)
    public String addCar(
            @ModelAttribute Car cars, @ModelAttribute Condition condition, @ModelAttribute Engine engine, HttpServletRequest req, HttpSession session
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
            User user = userDataRepository.getUserByLogin(session.getAttribute("login").toString()).orElseThrow(() ->new EntityNotFoundException(session.getAttribute("login").toString()));
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
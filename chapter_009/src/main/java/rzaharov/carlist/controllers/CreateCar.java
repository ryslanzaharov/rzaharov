package rzaharov.carlist.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import rzaharov.carlist.models.Car;
import rzaharov.carlist.models.Condition;
import rzaharov.carlist.models.Engine;
import rzaharov.carlist.models.User;
import rzaharov.carlist.repository.CarRepository;
import rzaharov.carlist.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CreateCar extends HttpServlet {

    static final int fileMaxSize = 1000 * 1024;
    static final int memMaxSize = 1000 * 1024;

    private static final ConcurrentMap<String, String> FORM = new ConcurrentHashMap<String, String>();

    private String filePath = "C:/projects/rzaharov/chapter_009/src/main/webapp/img/";
    private File file;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/carlist/CreateCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//управление параметрами запроса.
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);
        //процесс парсинга.
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);
        String fileUrl = "img/";
        try {
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
                    String fileName = fileItem.getName();
                    //создаем файл.
                    file = new File(filePath + fileName);
                    //записываем полученные данные в file на диске.
                    fileItem.write(file);
                    fileUrl += fileName;
                }
                else {
                    FORM.put(fileItem.getFieldName(), fileItem.getString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        Car car = new Car();
        car.setMark(FORM.get("mark"));
        car.setModel(FORM.get("model"));
        car.setBody_type(FORM.get("body_type"));
        car.setPrice(Integer.parseInt(FORM.get("price")));
        car.setSale(FORM.get("sale"));
        car.setEngine(new Engine(
                FORM.get("engine_name"),
                FORM.get("type_engine"),
                FORM.get("engine_condition")));
        car.setCondition(new Condition(
                FORM.get("condition_condition"),
                Integer.parseInt(FORM.get("year")),
                Integer.parseInt(FORM.get("mileage"))));
        User user = UserRepository.getInstance().getUserByLogin(session.getAttribute("login").toString()).get(0);
        car.setUser(user);
        car.setPhoto(fileUrl);
        CarRepository.getInstance().add(car);
        doGet(req, resp);
    }
}
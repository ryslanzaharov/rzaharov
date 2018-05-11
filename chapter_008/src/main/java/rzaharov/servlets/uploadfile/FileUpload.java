package rzaharov.servlets.uploadfile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Сервлет для загрузки файла на сервер.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 11.05.18.
 */

@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {

    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;

    private String filePath = "";
    private File file;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String docType = "<!DOCTYPE html>";
        String title = "File Uploading";

        PrintWriter writer = response.getWriter();

        writer.println(docType +
                "<html>" +
                "<head>" +
                "<title>" + title + "</title>" +
                "</head>" +
                "<body>");

        writer.println("File is uploaded.<br>");

        writer.println("</body>" +
                "</html>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//управление параметрами запроса.
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);
        //процесс парсинга.
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);
        try {
            //парсинг запроса.
            List fileItems = upload.parseRequest(request);
            //список запросов.
            Iterator iterator = fileItems.iterator();
            //получаем сведения о файле.
            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                //если это не обычное поле.
                if (!fileItem.isFormField()) {
                    //получаем название файла.
                    String fileName = fileItem.getName();
                    //оздаем файл.
                    file = new File(filePath + fileName);
                    //записываем полученные данные в file на диске.
                    fileItem.write(file);

                }
            }
        doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

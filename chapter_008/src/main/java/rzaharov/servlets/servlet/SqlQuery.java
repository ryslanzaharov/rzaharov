package rzaharov.servlets.servlet;

/**
 * Класс sql запросов.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 22.04.18.
 */

public class SqlQuery {

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS userdata(email VARCHAR(20) PRIMARY KEY," +
            " name VARCHAR, login VARCHAR, date TIMESTAMP)";

    public static final String INSERT = "INSERT INTO userdata(email, name, login, date) VALUES(?, ?, ?, ?)";

    public static final String SELECT = "SELECT * FROM userdata";

    public static final String SELECT_EMAIL = "SELECT * FROM userdata WHERE email = ?";

    public static final String UPDATE = "UPDATE userdata SET  email = ?, name = ?, login = ?, date = ? WHERE email = ?";

    public static final String DELETE = "DELETE FROM userdata WHERE email = ?";
}

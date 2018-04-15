package ru.job4j.parsing;

/**
 * Класс с запросам в бд.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 18.08.17.
 */

public class SqlQuery {

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS job(id SERIAL PRIMARY KEY ," +
            " vacancy TEXT, author TEXT, date TIMESTAMP, links TEXT)";

    public static final String INSERT = "INSERT INTO job(vacancy, author, date, links) VALUES(?, ?, ?, ?)";

    public static final String SELECT = "SELECT * FROM job ORDER BY date DESC";
}

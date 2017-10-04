package ru.job4j.listinmap;

import java.util.HashMap;
import java.util.List;
/**
 * Класс для конвертации списка в карты.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 04.10.17.
 */

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> userMap = new HashMap<>();
        for (User user : list) {
            userMap.put(user.getId(), user);
        }
        return userMap;
    }
}

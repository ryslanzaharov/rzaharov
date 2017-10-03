package ru.job4j.CollectionsFramework;

import java.util.*;

/**
 * Класс для измерения времени вставки в коллекцию большого количества случайных строк и удаления в коллекции первых n элементов для:
 *LinkedList.
 *ArrayList.
 *TreeSet.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 03.10.17.
 */
public class CollectionsTime {
    public long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        Random r = new Random();
        for (int i = 0; i < amount; i++) {
            collection.add("a"+ r.nextInt());
        }
        return System.currentTimeMillis() - start;
    }
    public long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        Iterator<String> iterator = collection.iterator();
        for (int i = 0; i < amount; i++) {
            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }
        return System.currentTimeMillis() - start;
    }
}

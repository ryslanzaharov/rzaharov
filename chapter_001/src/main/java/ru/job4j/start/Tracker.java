package ru.job4j.start;

import ru.job4j.models.*;
import java.util.*;

public class Tracker {
    ArrayList<Item> items = new ArrayList<>(10);
    private static final Random RN = new Random();

    public Item add(Item item) {
        if (item != null)
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    public void edit(Item fresh) {
        for (Item item : items) {
            int i = 0;
            if(item != null && item.getId().equals(fresh.getId())) {
                items.set(i , fresh);
                i++;
                break;
            }
        }
    }

    public Item findByName(String name) {
        Item item = null;
        for (Item itemid : items) {
            if (itemid != null && itemid.getName().equals(name))
                item = itemid;
        }
        return item;
    }

    public void delete(String id) {
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                items.remove(item);
            }
        }

    }

    protected Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public ArrayList<Item> findAll() {
        for (Item item : items) {
            if (item == null)
                items.remove(item);
        }
        return items;
    }

    public String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    public List<Item> getAll() {
        return items;
    }

    public void exit() {
        System.exit(0);
    }
}
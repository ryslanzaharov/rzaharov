package ru.job4j.start;

import ru.job4j.models.*;
import java.util.*;

public class Tracker {
    private Item[] items = new Item[10];
    private int position = 0;
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    public void edit(Item fresh) {
        for (int i = 0; i != items.length; ++i) {
            Item item = items[i];
            if(item != null && item.getId().equals(fresh.getId())) {
                items[i] = fresh;
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
        for (int i = 0;i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                items[i] = null;
            }
        }

    }

    protected Item findById(String id) {
        Item result = null;
        for (int i = 0;i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                result = items[i];
                break;
            }
        }
        return result;
    }

    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    public Item[] getAll() {
        Item[] result = new Item[position];
        for (int index = 0; index != position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    public void exit() {
        System.exit(0);
    }
}
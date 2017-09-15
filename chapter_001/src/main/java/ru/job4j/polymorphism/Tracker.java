package ru.job4j.polymorphism;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    private Item[] items = new Item[10];
    private int position = 0;
    private static final Random RN = new Random();
    ConsoleInput consoleInput = new ConsoleInput();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
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

    public void edit(int position, String name, String desc){
            items[position].setName(name);
            items[position].setDescription(desc);
            items[position].setId(generateId());
    }

    public void delete(int position) {
        System.arraycopy(items, position + 1, items, position, items.length - position - 2);
    }

    public Item findByName(String name) {
        Item item = null;
        for (Item itemid : items) {
            if (itemid != null && itemid.getName().equals(name))
                item = itemid;
        }
        return item;
    }

}
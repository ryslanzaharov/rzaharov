package ru.job4j.Tracker;

import java.util.Arrays;

public class Tracker {

    private Item[] items = new Item[100];
    private int position = 0;

    public Item add(Item item) {
         this.items[position++] = item;
         return item;
    }

    public Item[] findAll() {
        int c = 0;
        for (int i = items.length-1;i > 0; i--) {
            for (int n = 0;n < i;n++) {
                if (items[n] == null) {
                    items[n] = items[i];
                    items[i] = null;
                }
            }
        }
        for (Item item : items) {
            if (item != null)
                c++;
        }
        return Arrays.copyOf(items , c);
    }

    public Item[] findByName(String key) {
        int i = 0;
        for (Item item : items) {
            if ((item.getName()).equals(key)) {
                items[i++] = item;
            }
        }
        return Arrays.copyOf(items, i);
    }

    public Item findById(String id) {
        Item item = null;
        for (Item it : items) {
            if (id.equals(it.getId()))
                item = it;
        }
        return item;
    }

    public Item[] getAll() {
        return items;
    }
}

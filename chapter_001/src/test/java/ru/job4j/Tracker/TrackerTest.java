package ru.job4j.Tracker;

import org.junit.Test;
import ru.job4j.models.Item;
import ru.job4j.start.Tracker;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item);
        tracker.add(item);
        assertThat(tracker.getAll(), is(expected));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        item.setId("0");
        ArrayList<Item> expected = new ArrayList<>();
        tracker.delete(item.getId());
        assertThat(tracker.getAll(), is(expected));
    }

    @Test
    public void whenFindAllItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item1 = null;
        Item item2 = new Item("test2");
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item);
        expected.add(item2);
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        String expect = "test1";
        tracker.add(item);
        assertThat((tracker.getAll()).get(0).getName(), is(expect));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        item.setId("123");
        String expect = "123";
        tracker.add(item);
        assertThat((tracker.getAll()).get(0).getId(), is(expect));
    }


}

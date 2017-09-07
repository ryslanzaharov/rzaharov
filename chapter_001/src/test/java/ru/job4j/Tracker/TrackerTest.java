package ru.job4j.Tracker;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        item.setId("0");
        Item expected = null;
        tracker.delete(item);
        assertThat(tracker.getAll()[0], is(expected));
    }

    @Test
    public void whenFindAllItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item1 = null;
        Item item2 = new Item("test2");
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        Item[] expected = new Item[]{item, item2};
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        String expect = "test1";
        tracker.add(item);
        assertThat((tracker.getAll()[0]).getName(), is(expect));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        item.setId("123");
        String expect = "123";
        tracker.add(item);
        assertThat((tracker.getAll()[0]).getId(), is(expect));
    }

  //  @Test
//    public void whenFindByName() {
//        Tracker tracker = new Tracker();
//        Item item = new Item();
//
//        //Item[] item = new Item[]{new Item(),new Item(),null};
//        Item[] expected = new Item[]{new Item(),new Item()};
//        assertThat(tracker.findAll(), is(expected));
//    }
}

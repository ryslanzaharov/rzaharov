package ru.job4j.polymorphism;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUserGetAllItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name", "desc"));
        tracker.add(new Item("name1", "desc1"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.getAll()[1].getName(), is("name1"));
    }

    @Test
    public void whenUserEditItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name", "desc"));
        tracker.add(new Item("name1", "desc1"));
        Input input = new StubInput(new String[]{"2", "1", "name2", "desc2", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.getAll()[1].getName(), is("name2"));
    }

    @Test
    public void whenUserDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("name", "desc");
        Item item1 = new Item("name1", "desc1");
        Item item2 = new Item("name2", "desc2");
        Item[] items = {item , item1, null};
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        Input input = new StubInput(new String[]{"3", "2", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.getAll(), is(items));
    }

    @Test
    public void whenUserFindByIdItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("name", "desc");
        Item item1 = new Item("name1", "desc1");
        tracker.add(item);
        tracker.add(item1);
        String id = tracker.getAll()[1].getId();
        Input input = new StubInput(new String[]{"4", id, "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findById(id), is(item1));
    }

    @Test
    public void whenUserFindByNameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("name", "desc");
        Item item1 = new Item("name1", "desc1");
        tracker.add(item);
        tracker.add(item1);
        String name = tracker.getAll()[1].getName();
        Input input = new StubInput(new String[]{"5", name, "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findByName(name), is(item1));
    }

//    @Test
//    public void whenUpdateThenTrackerHasUpdatedValue() {
//        // создаём Tracker
//        Tracker tracker = new Tracker();
//        //Напрямую добавляем заявку
//        Item item = tracker.add(new Item());
//        //создаём StubInput с последовательностью действий
//        Input input = new StubInput(new String[]{"1", item.getId(), "test name", "desc", "6"});
//        // создаём StartUI и вызываем метод init()
//        new StartUI(input, tracker).init();
//        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
//        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
//    }
}

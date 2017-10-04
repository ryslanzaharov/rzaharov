package ru.job4j.start;
import ru.job4j.models.*;

import java.util.ArrayList;
import java.util.Arrays;

class EditItem extends BaseAction {
    public EditItem(String name, int key) {
        super(name, key);
    }

    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("please enter the task's id:");
        String name = input.ask("please enter the task's name:");
        String desc = input.ask("please enter the task's desc:");
        Task task = (new Task(name, desc));
        task.setId(id);
        tracker.edit(task);
    }

}

public class MenuTracker{

    private Input input;
    private Tracker tracker;
    private ArrayList<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        actions.addAll(Arrays.asList(
                this.new AddItem("Add the new item.", 0),
                new MenuTracker.ShowItems("Show all items", 1),
                new EditItem("Edit item", 2),
                this.new DeleteItem("Delete item", 3),
                this.new FindByIdItem("Find item by Id", 4),
                this.new FindByNameItem("Find items by name", 5),
                this.new Exit("Exit Program", 6) ));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem extends BaseAction {

        public AddItem(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("please enter the task name:");
            String desc = input.ask("please enter the task desc:");
            tracker.add(new Task(name, desc));
        }
    }

    private class DeleteItem extends BaseAction {

        public DeleteItem(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("please enter the task id:");
            tracker.delete(id);
        }

    }

    private class FindByIdItem extends BaseAction {

        public FindByIdItem(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("please enter the task id:");
            Item item = tracker.findById(id);
            System.out.println(String.format("%s, %s, %s", item.getName(), item.getDescription(), item.getId()));
        }

    }

    private class FindByNameItem extends BaseAction {

        public FindByNameItem(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("please enter the task name:");
            Item item = tracker.findByName(name);
            System.out.println(String.format("%s, %s, %s", item.getName(), item.getDescription(), item.getId()));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name.");
        }
    }

    private class Exit extends BaseAction {

        public Exit(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
            tracker.exit();
        }


    }

    private static class ShowItems extends BaseAction {

        public ShowItems(String name, int key) {
            super(name, key);
        }
        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                if (item != null)
                    System.out.println(String.format("%s, %s", item.getId(), item.getName())
                    );
            }
        }

    }
}
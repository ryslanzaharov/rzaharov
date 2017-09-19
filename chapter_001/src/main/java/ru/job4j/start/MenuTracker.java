package ru.job4j.start;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import ru.job4j.models.*;

class EditItem implements UserAction {

    public int key() {
        return 3;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("please enter the task's id:");
        String name = input.ask("please enter the task's name:");
        String desc = input.ask("please enter the task's desc:");
        Task task = (new Task(name, desc));
        task.setId(id);
        tracker.edit(task);
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Edit the new item.");
    }
}

public class MenuTracker{

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[8];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[1] = this.new AddItem();
        this.actions[2] = new MenuTracker.ShowItems();
        this.actions[3] = new EditItem();
        this.actions[4] = this.new DeleteItem();
        this.actions[5] = this.new FindByIdItem();
        this.actions[6] = this.new FindByNameItem();
        this.actions[7] = this.new Exit();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("please enter the task name:");
            String desc = input.ask("please enter the task desc:");
            tracker.add(new Task(name, desc));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }

    private class DeleteItem implements UserAction {

        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("please enter the task id:");
            tracker.delete(id);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Delete item.");
        }
    }

    private class FindByIdItem implements UserAction {

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("please enter the task id:");
            Item item = tracker.findById(id);
            System.out.println(String.format("%s, %s, %s", item.getName(), item.getDescription(), item.getId()));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id.");
        }
    }

    private class FindByNameItem implements UserAction {

        public int key() {
            return 6;
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

    private class Exit implements UserAction {

        public int key() {
            return 7;
        }

        public void execute(Input input, Tracker tracker) {
            tracker.exit();
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program.");
        }
    }

    private static class ShowItems implements UserAction {

        public int key() {
            return 2;
        }

        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                if (item != null)
                    System.out.println(String.format("%s, %s", item.getId(), item.getName())
                    );
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }
}
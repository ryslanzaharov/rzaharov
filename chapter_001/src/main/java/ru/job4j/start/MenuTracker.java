package ru.job4j.start;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import ru.job4j.models.*;

class EditItem extends BaseAction {
    public EditItem(String name, int key) {
        super(name, key);
    }

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
        this.actions[1] = this.new AddItem("Add the new item.", 1);
        this.actions[2] = new MenuTracker.ShowItems("Show all items", 2);
        this.actions[3] = new EditItem("Edit item", 3);
        this.actions[4] = this.new DeleteItem("Delete item", 4);
        this.actions[5] = this.new FindByIdItem("Find item by Id", 5);
        this.actions[6] = this.new FindByNameItem("Find items by name", 6);
        this.actions[7] = this.new Exit("Exit Program", 7);
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

    private class AddItem extends BaseAction {

        public AddItem(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 1;
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
            return 4;
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
            return 5;
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

    private class Exit extends BaseAction {

        public Exit(String name, int key) {
            super(name, key);
        }

        public int key() {
            return 7;
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
            return 2;
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
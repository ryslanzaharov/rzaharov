package ru.job4j.start;
import ru.job4j.models.*;

public class StartUI {

    private int[] ranges = new int[] {1,2,3,4,5,6,7};
    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }
    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select:", ranges));
            System.out.println("____________________");
        }while(true);
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input).init();
    }
}
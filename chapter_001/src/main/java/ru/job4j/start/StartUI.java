package ru.job4j.start;
import ru.job4j.models.*;

public class StartUI {
	
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
			int key = Integer.parseInt(input.ask("Select:"));
			menu.select(key);
			System.out.println("____________________");
		}while(true);
	}
	
	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
		}
}
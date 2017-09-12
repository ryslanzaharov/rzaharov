package ru.job4j.polymorphism;

public class StartUi {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDID = "4";
    private static final String FINDNAME = "5";
    private static final String EXIT = "6";

    public static void main(String[] args) {
        System.out.println("0. Add new Item\n" +
                "1. Show all items\n" +
                "2. Edit item\n" +
                "3. Delete item\n" +
                "4. Find item by Id\n" +
                "5. Find items by name\n" +
                "6. Exit Program\n" +
                "Select:");
        ConsoleInput consoleInput = new ConsoleInput();
        String answer;
        Tracker tracker = new Tracker();
        while (!(answer = consoleInput.ask()).equals(EXIT)) {
            if (answer.equals(ADD)) {
                System.out.print("Users name:");
                String name = consoleInput.ask();
                System.out.print("Items description:");
                String description = consoleInput.ask();
                tracker.add(new Item(name, description, tracker.generateId()));
            }
            else if (answer.equals(SHOW)) {
                    for (Item item : tracker.getAll()) {
                        if (item != null) {
                            System.out.println(item.getName());
                            System.out.println(item.getDescription());
                            System.out.println(item.getId());
                        }
                        else
                            System.out.println("null");
                    }
                }

            else if (answer.equals(EDIT)) {
                System.out.print("Items position:");
                int position = Integer.parseInt(consoleInput.ask());
                tracker.edit(position);
            }
            else if (answer.equals(DELETE)) {
                System.out.print("Items position:");
                tracker.delete(Integer.valueOf(consoleInput.ask()));
            }
            else if (answer.equals(FINDID)) {
                System.out.print("Items id:");
                Item item = tracker.findById(consoleInput.ask());
                System.out.println(item.getName());
                System.out.println(item.getDescription());
            }
            else if (answer.equals(FINDNAME)) {
                System.out.print("Users name:");
                Item item = tracker.findByName(consoleInput.ask());
                System.out.println(item.getName());
                System.out.println(item.getDescription());
            }
            else if (answer.equals(EXIT)) {
                return;
            }
        }
    }
}

package ru.job4j.machine;

/**
 * Класс вывода денежной сдачи при покупке с автомата.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 26.09.17.
 */
public class  Machine{

    public void automation() {
        Input input = new Input();
        Menu menu = new Menu();
        Money money = new Money();
        menu.fillActions();
        do {
            menu.show();
            menu.select(Integer.parseInt(input.ask("Select:")), money, input);
        } while (true);
    }

    public static void main(String[] args) {
        Machine parent = new Machine();
        parent.automation();
    }

}

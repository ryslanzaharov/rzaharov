package ru.job4j.multithreading.lift;
/**
 * Класс запуска лифта и класса ввода.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 09.03.18.
 */
public class LiftStart {

    public static void main(String[] args){
        Input input = new Input(Integer.parseInt(args[0]));
        Thread thInput = new Thread(input);

        Lift lift = new Lift(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), input);
        Thread thLift = new Thread(lift);

        thInput.start();
        thLift.start();

        try {
            thInput.join();
            thLift.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

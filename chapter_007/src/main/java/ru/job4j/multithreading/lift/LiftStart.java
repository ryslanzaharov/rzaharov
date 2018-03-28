package ru.job4j.multithreading.lift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс запуска лифта и класса ввода.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 09.03.18.
 */
public class LiftStart {

    public static void main(String[] args){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String ar0 = br.readLine();
            String ar1 = br.readLine();
            String ar2 = br.readLine();
            String ar3 = br.readLine();

        Input input = new Input(Integer.parseInt(ar0));
        Thread thInput = new Thread(input);

        Lift lift = new Lift(Integer.parseInt(ar0), Integer.parseInt(ar1),
                Integer.parseInt(ar2), Integer.parseInt(ar3), input);
        Thread thLift = new Thread(lift);

        thInput.start();
        thLift.start();


        try {
            thInput.join();
            thLift.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

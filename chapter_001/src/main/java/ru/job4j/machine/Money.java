package ru.job4j.machine;

import java.util.Arrays;

public class Money {
    private int price = 123;
    public int[] coins = {10, 10, 15, 33};

    public void addCoins(Input input) {
        coins[0] += Integer.parseInt(input.ask("10 coins:"));
        coins[1] += Integer.parseInt(input.ask("5 coins:"));
        coins[2] += Integer.parseInt(input.ask("2 coins:"));
        coins[3] += Integer.parseInt(input.ask("1 coins:"));
        System.out.println(Arrays.toString(coins));
    }

    public void buy(int summ) {
        int mod = summ - price;
        int tencoins = mod / 10;
        int tenmod = mod % 10;
        if (tencoins > coins[0]) {
            tenmod += (tencoins - coins[0]) * 10;
            tencoins = coins[0];
            coins[0] = 0;
        }
        else {
            coins[0] -= tencoins;
        }
        int fifthmod = tenmod % 5;
        System.out.println("f"+fifthmod);
        int fifthcoins = tenmod / 5;
        System.out.println("f"+fifthcoins);
        if (fifthcoins > coins[1]) {
            fifthmod += (fifthcoins - coins[1]) * 5;
            fifthcoins = coins[1];
            coins[1] = 0;
        }
        else {
            coins[1] -= fifthcoins;
        }
        int twocoins = fifthmod / 2;
        int onecoins = fifthmod % 2;
        if (twocoins > coins[2]) {
            onecoins += (twocoins - coins[2]) * 2;
            twocoins = coins[2];
            coins[2] = 0;
        }
        else {
            coins[3] -= twocoins;
        }
        if (onecoins > coins[3]) {
            onecoins = coins[3];
            coins[3] = 0;
        }
        System.out.println(String.format("money transfer 10: %s ,money transfer 5: %s ,money transfer 2: %s ,money transfer 1: %s",
                tencoins, fifthcoins , twocoins, onecoins));
        System.out.println(String.format("10 rubles left %s , 5 rubles left %s, 2 rubles left %s, 1rubles left %s",
                coins[0], coins[1], coins[2], coins[3]));
        if ((coins[0] ==0) || (coins[1] ==0) || (coins[2]==0) || (coins[3] ==0))
            System.out.println("Add new coins!");
    }
}

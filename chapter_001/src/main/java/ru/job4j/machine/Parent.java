package ru.job4j.machine;
/**
 * Класс вывода денежной сдачи при покупке с автомата.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 26.09.17.
 */
public class Parent {

    private int price;
    public Parent(int price) {
        this.price = price;
    }

    class Child {
        private int summ;
        private int price;
        public Child(int summ) {
            this.summ = summ;
            this.price = price;
        }

        public int getPrice() {
            return price;
        }

        public int getSumm() {
            return summ;
        }

        public void setSumm(int summ) {
            this.summ = summ;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public int getPrice() {
        return this.price;
    }
    public static void main(String[] args) {
        Parent parent = new Parent(130);
        Child child = parent.new Child(200);
        System.out.println(parent.getMoneyTransfer(child.getSumm(), parent.getPrice()));
    }

    public int getMoneyTransfer(int summ, int price) {
        return summ - price;
    }
}

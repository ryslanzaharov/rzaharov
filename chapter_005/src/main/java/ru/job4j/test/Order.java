package ru.job4j.test;

public class Order implements Comparable<Order> {

    private String book;
    private String operation;
    private double price;
    private int volume;
    private int orderId;

    public Order(String book, String operation, double price, int volume, int orderId) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    public Order(String book, int orderId) {
        this.book = book;
        this.orderId = orderId;
    }


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "price=" + price + ", volume=" + volume + " buy " + operation;
    }

    @Override
    public int compareTo(Order o) {
        int val = this.operation.compareTo(o.operation);
        if (val == 0)
            val = (int)(this.price - o.price);
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return Double.compare(order.price, price) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(price);
        return (int) (temp ^ (temp >>> 32));
    }
}

package ru.job4j.sorting;

public class User implements Comparable<User>{
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int compareTo(User o) {
        final int ageComp = Integer.compare(this.age, o.age);
        return ageComp != 0 ? ageComp : this.name.compareTo(o.name);
    }

    public String toString() {
        return "User{ name = '" + name + "', age = '" + age + "'}";
    }

}

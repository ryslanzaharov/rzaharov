package ru.job4j.inheritance;

public class Profession {
    public String name;
    public int age;
    Profession[] professions = new Profession[]{new Doctor("Иван"),new Teacher("Александр"),new Engineer("Руслан"),
            new Student("Петя"), new Sick("Вова"), new Invention("стул")};


    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}

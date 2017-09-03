package ru.job4j.inheritance;

public class Teacher extends Profession {
    private String chapter;
    private String school;

    public Teacher(String name) {
        this.name = name;
    }

    public Knowledge teach(Student student) {
        String pname = null;
        String sname = null;
        Professions prof = new Professions();
        for (Profession profname : prof.professions){
            if (profname instanceof Teacher) {
                pname =((Teacher) profname).getName();
            }
            if (profname instanceof Student) {
                sname =((Student) profname).getName();
            }
        }
        return new Knowledge("Учитель"+pname+"учит"+sname);
    }
}

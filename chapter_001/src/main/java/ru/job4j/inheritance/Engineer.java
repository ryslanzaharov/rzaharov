package ru.job4j.inheritance;

public class Engineer extends Profession {
    private String diploma;
    private String office;

    public Engineer(String name) {
        this.name = name;
    }

    public Tool create(Invention invention) {
        String pname = null;
        String iname = null;
        for (Profession profname : professions){
            if (profname instanceof Engineer) {
                pname =((Engineer) profname).getName();
            }
            if (profname instanceof Invention) {
                iname =((Invention) profname).getName();
            }
        }
        return new Tool("Инженер"+pname+"создает"+iname);
    }
}

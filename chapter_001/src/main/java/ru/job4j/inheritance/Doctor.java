package ru.job4j.inheritance;

    public class Doctor extends Profession {
    private String diploma;
    private String hospital;

    public Doctor(String name) {
        this.name = name;
    }

    public Medicament treat(Sick sick) {
        String pname = null;
        String sname = null;
        for (Profession profname : professions){
            if (profname instanceof Doctor) {
                pname =((Doctor) profname).getName();
            }
            if (profname instanceof Sick) {
                sname =((Sick) profname).getName();
            }
        }
        return new Medicament("Доктор"+pname+"лечит"+sname);
    }
}

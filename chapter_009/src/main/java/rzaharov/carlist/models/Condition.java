package rzaharov.carlist.models;

import javax.persistence.*;

//@Entity
//@Table(name = "condition")
public class Condition {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private int id;

 //   @Column(name = "condition")
    private String condition;

 //   @Column(name = "year")
    private int year;

 //   @Column(name = "mileage")
    private int mileage;

    public Condition(int id, String condition, int year, int mileage) {
        this.condition = condition;
        this.year = year;
        this.mileage = mileage;
        this.id = id;
    }

    public Condition(String condition, int year, int mileage) {
        this.condition = condition;
        this.year = year;
        this.mileage = mileage;
    }

    public Condition() {}

    public Condition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", condition='" + condition + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                '}';
    }
}

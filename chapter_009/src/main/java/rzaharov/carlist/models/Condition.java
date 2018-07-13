package rzaharov.carlist.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "condition")
@Proxy(lazy = false)
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "condition")
    @Getter
    @Setter
    private String condition;

    @Column(name = "year")
    @Getter
    @Setter
    private int year;

    @Column(name = "mileage")
    @Getter
    @Setter
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

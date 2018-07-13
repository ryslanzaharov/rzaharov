package rzaharov.carlist.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Proxy(lazy = false)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "mark")
    @Getter
    @Setter
    private String mark;

    @Column(name = "model")
    @Getter
    @Setter
    private String model;

    @Column(name = "body_type")
    @Getter
    @Setter
    private String body_type;

    @Column(name = "price")
    @Getter
    @Setter
    private Integer price;

    @Column(name = "sale")
    @Getter
    @Setter
    private String sale;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    @Getter
    @Setter
    private Engine engine;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_id")
    @Getter
    @Setter
    private Condition condition;

    @Column(name = "photo")
    @Getter
    @Setter
    private String photo;

    public Car() {}

    public Car(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", body_type='" + body_type + '\'' +
                ", price=" + price +
                ", sale='" + sale + '\'' +
                ", engine=" + engine +
            //    ", user=" + user +
                ", condition=" + condition +
                ", photo='" + photo + '\'' +
                '}';
    }
}

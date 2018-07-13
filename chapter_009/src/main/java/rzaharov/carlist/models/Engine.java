package rzaharov.carlist.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "engine")
@Proxy(lazy = false)
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "type_engine")
    @Getter
    @Setter
    private String type_engine;

    @Column(name = "condition")
    @Getter
    @Setter
    private String condition;

    public Engine() {}

    public Engine(int id, String name, String type_engine, String condition) {
        this.name = name;
        this.type_engine = type_engine;
        this.condition = condition;
        this.id = id;
    }

    public Engine(String name, String type_engine, String condition) {
        this.name = name;
        this.type_engine = type_engine;
        this.condition = condition;
    }

    public Engine(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type_engine='" + type_engine + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}

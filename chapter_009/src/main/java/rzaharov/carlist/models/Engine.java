package rzaharov.carlist.models;

import javax.persistence.*;
import java.util.List;

//@Entity
//@Table(name = "engine")
public class Engine {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private int id;

 //   @Column(name = "name")
    private String name;

 //   @Column(name = "type_engine")
    private String type_engine;

 //   @Column(name = "condition")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_engine() {
        return type_engine;
    }

    public void setType_engine(String type_engine) {
        this.type_engine = type_engine;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

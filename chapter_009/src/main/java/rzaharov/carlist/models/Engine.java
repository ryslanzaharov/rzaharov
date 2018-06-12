package rzaharov.carlist.models;

import java.util.List;

public class Engine {

    private int id;
    private String name;
    private String type_engine;
    private String condition;
    private List<Car> cars;

    public Engine() {}

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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
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

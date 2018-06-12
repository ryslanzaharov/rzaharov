package rzaharov.carlist.models;

public class Condition {

    private int id;
    private String condition;
    private int year;
    private int mileage;
    private Car car;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", condition='" + condition + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", car=" + car +
                '}';
    }
}

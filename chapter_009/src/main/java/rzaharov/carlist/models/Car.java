package rzaharov.carlist.models;

import javax.persistence.*;

//@Entity
//@Table(name = "car")
public class Car {

   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column(name = "id")
    private int id;

   // @Column(name = "mark")
    private String mark;

    //@Column(name = "model")
    private String model;

  //  @Column(name = "body_type")
    private String body_type;

   // @Column(name = "price")
    private Integer price;

  //  @Column(name = "sale")
    private String sale;

//    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "engine_id")
    private Engine engine;

//    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "condition_id")
    private Condition condition;

  //  @Column(name = "photo")
    private String photo;

    public Car() {}

    public Car(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

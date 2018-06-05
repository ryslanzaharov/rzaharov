package rzaharov.servlets.cop.musiccourt.models;

import java.io.Serializable;

public class Model implements Serializable{

    private Integer id;

    public Model(){}

    public Model(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

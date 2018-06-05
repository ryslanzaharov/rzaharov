package rzaharov.servlets.cop.musiccourt.models;

public class Role extends Model {

    private String name;

    public Role() {
        super();
    }

    public Role(Integer id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

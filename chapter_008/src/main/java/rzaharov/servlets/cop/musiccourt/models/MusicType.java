package rzaharov.servlets.cop.musiccourt.models;

import java.util.List;

public class MusicType extends Model {

    private String name;

    public MusicType() {
        super();
    }

    public MusicType(Integer id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

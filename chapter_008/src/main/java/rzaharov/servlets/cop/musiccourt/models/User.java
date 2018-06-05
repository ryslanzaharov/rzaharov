package rzaharov.servlets.cop.musiccourt.models;

import java.util.List;

public class User extends Model {

    private String name;
    private String surname;
    private Integer role_id;
    private String login;
    private String password;
    private Role role;
    private List<MusicType> musicTypes;
    private Address address;

    public User() {
        super();
    }

    public User(Integer id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<MusicType> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(List<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.model.enums.UserType;

import java.util.Date;

public class UserDTO {
    private String id;
    private String name;
    private String username;
    private String email;
    private UserType userType;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userType = user.getUserType();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.enums.UserRole;

public class PersonRequest {
    private String username;
    private String password;
    private String email;
    private UserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){return email;}

    public void setEmail(){this.email = email;}

    public UserRole getRole(){
        return role;
    }

    public void setRole(UserRole role){
        this.role = role;
    }
}

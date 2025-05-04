package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public class PersonRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Enumerated(EnumType.STRING)
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


    public UserRole getRole(){
        return role;
    }

    public void setRole(UserRole role){
        this.role = role;
    }
}

package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public class PersonResponse {
    @NotBlank
    private String username;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public PersonResponse(String username, UserRole role){
        this.username = username;
        this.role = role;
    }

    public UserRole getRole(){
        return role;
    }

    public void setRole(){
        this.role=role;
    }

    public String getUsername(){return username;}

    public void setUsername(){this.username = username;}
}

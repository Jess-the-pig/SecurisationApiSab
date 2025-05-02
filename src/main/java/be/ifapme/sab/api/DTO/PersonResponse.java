package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.Person;
import be.ifapme.sab.model.entities.enums.UserRole;

public class PersonResponse {
    private String username;
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

package be.ifapme.sab.api.DTO;

import be.ifapme.sab.model.entities.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OAuthPersonRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public OAuthPersonRequest(String name, String email,UserRole role){
        this.name = name ;
        this.email = email;
        this.role = role;
    }
    public UserRole getRole(){
        return role;
    }

    public void setRole(UserRole role){
        this.role=role;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

}

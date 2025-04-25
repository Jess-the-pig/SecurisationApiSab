package be.ifapme.sab.model.entities;

import be.ifapme.sab.model.entities.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "person", schema = "public")
public class OAuthPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private String passwordHash;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public OAuthPerson(Long id, String email,String passwordHash,UserRole role){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;

    }
    public OAuthPerson(){
    }

    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash(){
        return passwordHash;
    }



    public UserRole getRole(){
        return role;
    }

    public void setRole(UserRole role){
        this.role=role;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}

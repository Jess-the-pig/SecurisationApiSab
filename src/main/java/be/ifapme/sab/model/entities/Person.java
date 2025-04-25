package be.ifapme.sab.model.entities;

import be.ifapme.sab.model.entities.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "person", schema = "public")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @JsonIgnore
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public Person() {
    }

    public Person(String passwordHash, UserRole role, String email) {
        this.passwordHash = passwordHash;
        this.role = role;
        this.email = email;
    }


    public Long getId() {
        return id;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }



    public void setPasswordHash(String passwordHash){this.passwordHash = passwordHash;}
    public void setRole(UserRole userRole){this.role = role;}
    public void setEmail(String email){this.email = email;}


}

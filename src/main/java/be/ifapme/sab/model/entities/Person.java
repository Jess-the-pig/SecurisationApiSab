package be.ifapme.sab.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Person() {
    }

    public Person(String username, String passwordHash, UserRole role, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username){this.username = username;}
    public void setPasswordHash(String passwordHash){this.passwordHash = passwordHash;}
    public void setUserRole(UserRole userRole){this.role = role;}
    public void setEmail(String email){this.email = email;}

}

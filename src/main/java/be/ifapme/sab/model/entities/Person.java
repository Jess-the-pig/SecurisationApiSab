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

    private String username;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public Person() {
    }

    public Person(String password, UserRole role, String username) {
        this.password = password;
        this.role = role;
        this.username = username;
    }


    public Long getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }



    public void setPassword(String password){this.password = password;}
    public void setRole(UserRole userRole){this.role = role;}
    public void setUsername(String username){this.username = username;}


}

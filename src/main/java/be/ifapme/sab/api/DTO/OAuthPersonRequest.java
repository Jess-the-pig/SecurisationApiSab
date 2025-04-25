package be.ifapme.sab.api.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OAuthPersonRequest {
    private String name;
    private String email;
    private String role;

    public OAuthPersonRequest(String name, String email,String role){
        this.name = name ;
        this.email = email;
        this.role = role;
    }
    public String getRole(){
        return role;
    }

    public void setRole(String role){
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

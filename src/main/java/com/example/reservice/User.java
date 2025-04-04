package com.example.reservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    Integer id;

    private String email;
    private String fullName;
    private String password;

    public User(){}

    public User(UserDTO userDTO) {
        this.email = userDTO.getEmail();
        this.fullName = userDTO.getFullName();
        this.password = userDTO.getPassword();
        this.id = userDTO.getId();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

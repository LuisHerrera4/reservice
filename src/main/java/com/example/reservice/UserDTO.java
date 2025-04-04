package com.example.reservice;

public class UserDTO {

    private int id;
    String email;
    String fullName;
    String password;

    public UserDTO(){}

    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.password = user.getPassword();
    }

    public int getId() {
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
}

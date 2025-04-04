package com.example.reservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    UserDAO userDAO;


    public List<User> getUsers(){

        return userDAO.findAll();
    }

    public User getUserById(int id) {
        Optional<User> u = userDAO.findById(id);
        if (u.isPresent()) return u.get();
        else return null;
    }

    public User newUser (UserDTO userDTO){
        User u = new User(userDTO);
        return userDAO.save(u);
    }

   /* public void deleteUser(int id) {
        userRepository.delete(id);
    }
    */
}

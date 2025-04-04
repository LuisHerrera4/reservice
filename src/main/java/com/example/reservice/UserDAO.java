package com.example.reservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findAll();
    Optional<User> findById(Integer id);
    //Optional<UserDTO> findUserByEmail(String email);
    //Optional<UserDTO> findUserByFullNameAndEmail(String fullName, String email);




}

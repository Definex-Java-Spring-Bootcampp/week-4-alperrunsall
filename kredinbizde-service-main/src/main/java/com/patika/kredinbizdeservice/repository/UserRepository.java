package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("select u FROM User u where u.email = :email")
    Optional<User> findByEmail(String email);

}

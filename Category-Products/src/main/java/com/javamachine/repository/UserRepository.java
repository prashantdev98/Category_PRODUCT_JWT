package com.javamachine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javamachine.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);
    List<Optional<User>> findByUserNameStartsWith(String username);
    List<Optional<User>> findByUserNameLike(String username);
    List<Optional<User>> findByUserNameEndsWith(String username);
    Optional<User> findByEmail(String username);
    
    public boolean existsUserByEmail(String email);//existsCarByModel
    
    int deleteByUserName(String userName);
    
}
package com.javamachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javamachine.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
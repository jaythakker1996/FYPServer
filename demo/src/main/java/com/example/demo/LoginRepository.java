package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

//Middle layer between the DB and Program
public interface LoginRepository extends JpaRepository<User, String> {

}

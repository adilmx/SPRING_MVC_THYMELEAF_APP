package com.adilmx.spring_mvc_app.repository;

import com.adilmx.spring_mvc_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}

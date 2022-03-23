package com.adilmx.spring_mvc_app.repository;

import com.adilmx.spring_mvc_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}

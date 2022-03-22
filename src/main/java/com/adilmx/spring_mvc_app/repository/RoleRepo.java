package com.adilmx.spring_mvc_app.repository;

import com.adilmx.spring_mvc_app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}

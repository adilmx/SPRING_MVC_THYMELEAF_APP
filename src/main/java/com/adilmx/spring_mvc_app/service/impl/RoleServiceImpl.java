package com.adilmx.spring_mvc_app.service.impl;

import com.adilmx.spring_mvc_app.entities.Role;
import com.adilmx.spring_mvc_app.repository.RoleRepo;
import com.adilmx.spring_mvc_app.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }
}

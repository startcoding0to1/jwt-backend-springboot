package com.startcoding0to1.backend.serviceImpl;

import com.startcoding0to1.backend.repository.RoleRepository;
import com.startcoding0to1.backend.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleDao;

    public Role createNewRole(Role role){
        return roleDao.save(role);
    }
}

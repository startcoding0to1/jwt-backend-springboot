package com.startcoding0to1.backend.controller;

import com.startcoding0to1.backend.entity.Role;
import com.startcoding0to1.backend.serviceImpl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/startcoding0to1/admin")
//@PreAuthorize("hasRole('Admin')")//if you configure in websecurity then here no need
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createNewRole")
    public Role createNewRole(@RequestBody Role roleDto){
        return  roleService.createNewRole(roleDto);
    }
}

package com.example.springboot.Service;

import com.example.springboot.Model.Role;
import com.example.springboot.Model.User;
import com.example.springboot.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class RoleService {


    @Autowired
    RoleRepository roleRepository;


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void add(Role role) {
        roleRepository.save(role);
    }


    public Set<Role> getSetRoles(Set<String> roles) {
        return roleRepository.getSetRoles(roles);

    }

}

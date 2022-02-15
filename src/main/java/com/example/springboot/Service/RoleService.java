package com.example.springboot.Service;

import com.example.springboot.Model.Role;
import com.example.springboot.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;


@Service
public class RoleService {


    @Autowired
    RoleRepository roleRepository;


    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }


    public Set<Role> getSetRoles(Set<String> roles) {
        return roleRepository.getSetRoles(roles);

    }

}

package com.example.springboot.Repository;

import com.example.springboot.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface RoleRepository extends  JpaRepository<Role,Long> {



@Query(value = "select role from Role role where role.name = :roles")
    Set<Role> getSetRoles(@Param("roles") Set<String>roles);


}

package com.example.springboot.Repository;

import com.example.springboot.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends  JpaRepository<Role,Long> {


@Modifying
@Query(value = "select role from Role role where  role.name  in :roles")
    Set<Role> getSetRoles(@Param("roles") Set<String>roles);


}

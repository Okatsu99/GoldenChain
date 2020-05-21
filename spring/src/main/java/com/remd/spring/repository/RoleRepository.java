package com.remd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

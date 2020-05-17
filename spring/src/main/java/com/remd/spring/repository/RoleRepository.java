package com.remd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.bean.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

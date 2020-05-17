package com.remd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.bean.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

}

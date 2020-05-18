package com.remd.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.bean.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

}

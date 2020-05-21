package com.remd.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
	Optional<User> findByEmail(String email);
	List<User> findByDoctorId(Integer doctorId);
}

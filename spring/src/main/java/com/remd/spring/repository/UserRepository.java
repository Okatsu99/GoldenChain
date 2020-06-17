package com.remd.spring.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.remd.spring.model.Clinic;
import com.remd.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
	Optional<User> findByEmail(String email);
	List<User> findByDoctorId(Integer doctorId);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE User user SET user.firstName=?1, user.lastName=?2, user.email=?3, user.cellphoneNumber=?4, user.clinic=?5 where user.id=?6")
	int editSecretaryById(String firstName, String lastName, String email, String cellNumber,Clinic clinic, Integer id);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query("UPDATE User user SET user.passWord=?1 where user.id=?2")
	int updatePassword(String password, Integer id);
}

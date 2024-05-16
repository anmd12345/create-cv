package com.tdmu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tdmu.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	@Query(value = "select * from [user] where username = :username and password = :password", nativeQuery = true)
	User doLogin(String username, String password);
}

package com.tdmu.service;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tdmu.entity.User;

public interface UserService {
	User doLogin(User user);

	User save(User user, HttpSession session) throws SQLException;

	List<User> findAll();

	User deletedUser(String username);

	User doLogin(String username, String password);
}

package com.tdmu.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdmu.entity.Roles;
import com.tdmu.entity.User;
import com.tdmu.repository.UserRepo;
import com.tdmu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Override
	public User doLogin(User user) {
		User userRequest = repo.findByUsername(user.getUsername());
		if (ObjectUtils.isNotEmpty(userRequest)) {
			if (userRequest.getPassword().equals(user.getPassword()) && userRequest.getIsDeleted() == false) {
				return userRequest;
			}
		}
		return null;
	}

	@Override
	@Transactional(rollbackOn = Throwable.class)
	public User save(User user, HttpSession session) throws SQLException {
		Roles roles = (Roles) session.getAttribute("roleSession");
		user.setRoles(roles);
		user.setIsDeleted(Boolean.FALSE);
		return repo.saveAndFlush(user);
	}

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public User deletedUser(String username) {
		User userRequest = repo.findByUsername(username);
		if (userRequest.getIsDeleted() == true) {
			userRequest.setIsDeleted(Boolean.FALSE);
		} else {
			userRequest.setIsDeleted(Boolean.TRUE);
		}
		return repo.saveAndFlush(userRequest);
	}

	@Override
	public User doLogin(String username, String password) {
		User userRequest = repo.doLogin(username, password);
		return ObjectUtils.isEmpty(userRequest) ? null : userRequest;
	}
}

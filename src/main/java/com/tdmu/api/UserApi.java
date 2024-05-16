package com.tdmu.api;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdmu.constant.UserConstant;
import com.tdmu.entity.User;
import com.tdmu.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApi {

	@Autowired
	private UserService userService;

	@GetMapping("/deleted")
	public ResponseEntity<?> deletedUser(@RequestParam String username) {
		User userRequest = userService.deletedUser(username);
		if (ObjectUtils.isNotEmpty(userRequest)) {
			return ResponseEntity.ok(userRequest);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/login")
	public ResponseEntity<?> doLogin(@RequestParam String username, @RequestParam String password,
			HttpSession session) {
		User userRequest = userService.doLogin(username, password);
		if (ObjectUtils.isNotEmpty(userRequest)) {
			session.setAttribute(UserConstant.CURRENT_USER, userRequest);
			return ResponseEntity.ok(userRequest);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}

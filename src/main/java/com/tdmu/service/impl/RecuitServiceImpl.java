package com.tdmu.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdmu.entity.Recruit;
import com.tdmu.entity.User;
import com.tdmu.repository.RecuitRepo;
import com.tdmu.service.RecuitService;

@Service
public class RecuitServiceImpl implements RecuitService {

	@Autowired
	private RecuitRepo repo;

	@Override
	public Recruit createdNewRecuit(String description, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		Recruit recuit = new Recruit();
		recuit.setDescription(description);
		recuit.setUser(user);
		recuit.setIsDeleted(false);
		recuit.setName(user.getUsername());
		return repo.saveAndFlush(recuit);
	}

	@Override
	public List<Recruit> findAll() {
		return repo.findAll();
	}

	@Override
	public Recruit deletedRecruit(Long id) {
		Recruit recruit = repo.findById(id);
		recruit.setIsDeleted(true);
		return repo.saveAndFlush(recruit);
	}

	@Override
	public Recruit updateRecruit(Long id, String description) {
		Recruit recruit = repo.findById(id);
		recruit.setDescription(description);
		return repo.saveAndFlush(recruit);
	}

}

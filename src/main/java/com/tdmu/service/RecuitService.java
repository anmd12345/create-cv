package com.tdmu.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tdmu.entity.Recruit;

public interface RecuitService {
	Recruit createdNewRecuit(String description, HttpSession session);
	List<Recruit> findAll();
	Recruit deletedRecruit(Long id);
	Recruit updateRecruit(Long id, String description);
}

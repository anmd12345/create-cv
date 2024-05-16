package com.tdmu.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdmu.constant.CVConstant;
import com.tdmu.entity.CV;
import com.tdmu.entity.Experiences;
import com.tdmu.repository.ExperiencesRepo;
import com.tdmu.service.ExperiencesService;

@Service
public class ExperiencesServiceImpl implements ExperiencesService {

	@Autowired
	private ExperiencesRepo repo;

	@Override
	public List<Experiences> getAllExperiencesById(Long id) {
		return repo.getAllExperiencesById(id);
	}

	@Override
	public Experiences save(Experiences experiences, HttpSession session) throws SQLException {
		CV cv = (CV) session.getAttribute(CVConstant.CURRENT_CV);
		experiences.setCv(cv);
		experiences.setIsDeleted(false);
		return repo.saveAndFlush(experiences);
	}

}

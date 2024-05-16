package com.tdmu.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.tdmu.entity.Experiences;

public interface ExperiencesService {
	List<Experiences> getAllExperiencesById(Long id);

	Experiences save(Experiences experiences, HttpSession session) throws SQLException;
}

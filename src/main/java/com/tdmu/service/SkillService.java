package com.tdmu.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.tdmu.entity.Skill;

public interface SkillService {
	List<Skill> getAllSkillById(Long id);

	List<Skill> findAll(Long id);

	Skill save(Skill skill, HttpSession session) throws SQLException;

	Skill deletedSkillById(Long id);

	Skill findById(Long id);

	Skill updateSkillById(Long id, String skillName, String level);

	Skill updateSkill(Long id, String skillName, String level);
}

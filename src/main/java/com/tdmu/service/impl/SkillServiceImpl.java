package com.tdmu.service.impl;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdmu.constant.CVConstant;
import com.tdmu.entity.CV;
import com.tdmu.entity.Skill;
import com.tdmu.repository.SkillRepo;
import com.tdmu.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepo repo;

	@Override
	public List<Skill> getAllSkillById(Long id) {
		return repo.getAllSkillById(id);
	}

	@Override
	public Skill save(Skill skill, HttpSession session) throws SQLException {
		CV cv = (CV) session.getAttribute(CVConstant.CURRENT_CV);
		skill.setCv(cv);
		skill.setIsDeleted(false);
		skill.setIcon("fa fa-globe circle circle--small circle--white");
		return repo.saveAndFlush(skill);
	}

	@Override
	public List<Skill> findAll(Long id) {
		return repo.findAll(id);
	}

	@Override
	public Skill deletedSkillById(Long id) {
		Skill skill = repo.findById(id);
		skill.setIsDeleted(true);
		return repo.saveAndFlush(skill);
	}

	@Override
	public Skill findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Skill updateSkillById(Long id, String skillName, String level) {
		Skill skillRequest = repo.findById(id);
		skillRequest.setIsDeleted(true);
		skillRequest.setSkillName(skillName);
		skillRequest.setLevel(level);
		return repo.saveAndFlush(skillRequest);
	}

	@Override
	public Skill updateSkill(Long id, String skillName, String level) {
		Skill skillRequest = repo.findById(id);
		skillRequest.setSkillName(skillName);
		skillRequest.setLevel(level);
		return repo.saveAndFlush(skillRequest);
	}
}

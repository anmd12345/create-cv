package com.tdmu.api;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdmu.entity.Skill;
import com.tdmu.service.SkillService;

@RestController
@RequestMapping("/api/skill")
public class SkillApi {
	@Autowired
	private SkillService skillService;

	@GetMapping("/deleted")
	public ResponseEntity<?> doGetDeletedById(@RequestParam Long id) {
		Skill skillRequest = skillService.deletedSkillById(id);
		if (ObjectUtils.isEmpty(skillRequest)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(skillRequest);
	}

	@GetMapping("/update")
	public ResponseEntity<?> updateSkillById(@RequestParam Long id, @RequestParam String skillName,
			@RequestParam String level) {
		Skill skill = skillService.updateSkillById(id, skillName, level);
		if (ObjectUtils.isEmpty(skill)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(skill);
	}

	@GetMapping("/updateR")
	public ResponseEntity<?> updateSkill(@RequestParam Long id, @RequestParam String skillName,
			@RequestParam String level) {
		Skill skill = skillService.updateSkill(id, skillName, level);
		if (ObjectUtils.isEmpty(skill)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(skill);
	}

	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefresh(@RequestParam Long id) {
		List<Skill> skill = skillService.getAllSkillById(id);
		return ResponseEntity.ok(skill);
	}
}

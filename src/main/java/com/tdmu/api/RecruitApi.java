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

import com.tdmu.entity.Recruit;
import com.tdmu.service.RecuitService;

@RestController
@RequestMapping("/api/recuit")
public class RecruitApi {

	@Autowired
	private RecuitService recuitService;

	@GetMapping("newRecuit")
	public ResponseEntity<?> doGetNewRecuit(@RequestParam String description, HttpSession session) {
		Recruit recuit = recuitService.createdNewRecuit(description, session);
		if (ObjectUtils.isNotEmpty(recuit)) {
			return ResponseEntity.ok(recuit);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("deleted")
	public ResponseEntity<?> deletedRecruit(@RequestParam Long id) {
		Recruit recruit = recuitService.deletedRecruit(id);
		if (ObjectUtils.isEmpty(recruit)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(recruit);
	}
	
	@GetMapping("update")
	public ResponseEntity<?> updateRecruit(@RequestParam Long id, @RequestParam String description) {
		Recruit recruit = recuitService.updateRecruit(id, description);
		if (ObjectUtils.isEmpty(recruit)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(recruit);
	}


}

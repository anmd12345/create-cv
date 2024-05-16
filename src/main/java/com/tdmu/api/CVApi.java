package com.tdmu.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdmu.constant.CVConstant;
import com.tdmu.entity.CV;

import com.tdmu.service.CVService;

@RestController
@RequestMapping("/api/cv")
public class CVApi {

	@Autowired
	private CVService cvService;

	@GetMapping("")
	public ResponseEntity<?> doGetAll() {
		List<CV> cv = cvService.findAll();
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/details/{id}")
	public ResponseEntity<?> doGetById(@PathVariable("id") Long id, HttpSession session) {
		CV cv = cvService.findById(id);
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		session.setAttribute(CVConstant.CURRENT_CV, cv);
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/deleted")
	public ResponseEntity<?> doGetDeletedById(@RequestParam Long id) {
		CV cvResphonse = cvService.deletedCVById(id);
		if (ObjectUtils.isEmpty(cvResphonse)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cvResphonse);
	}

	@GetMapping("/updateFullName")
	public ResponseEntity<?> doGetUpdateFullNameById(@RequestParam Long id, @RequestParam String firstName,
			@RequestParam String lastName) {
		CV cv = cvService.updateFullNameById(id, firstName, lastName);
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/updateViTriUngTuyen")
	public ResponseEntity<?> doGetUpdateViTriUngTuyenById(@RequestParam Long id, @RequestParam String viTriUngTuyen) {
		CV cv = cvService.updateViTriUngTuyenById(id, viTriUngTuyen);
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/updateAboutMe")
	public ResponseEntity<?> doGetUpdateAboutMeById(@RequestParam Long id, @RequestParam String aboutMe) {
		CV cv = cvService.updateAboutMeById(id, aboutMe);
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/updateAddress")
	public ResponseEntity<?> doGetUpdateAddressById(@RequestParam Long id, @RequestParam String address) {
		CV cv = cvService.updateAddressById(id, address);
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/updateEmail")
	public ResponseEntity<?> doGetUpdateEmailById(@RequestParam Long id, @RequestParam String email) {
		CV cv = cvService.updateEmailById(id, email);
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/updatePhone")
	public ResponseEntity<?> doGetUpdatePhoneById(@RequestParam Long id, @RequestParam String phone) {
		CV cv = cvService.updatePhoneById(id, phone);
		if (ObjectUtils.isEmpty(cv)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cv);
	}

	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefresh(Long id) {
		CV cv = cvService.findById(id);
		return ResponseEntity.ok(cv);
	}
}

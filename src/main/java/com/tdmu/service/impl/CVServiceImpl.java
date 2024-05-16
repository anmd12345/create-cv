package com.tdmu.service.impl;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdmu.constant.UserConstant;
import com.tdmu.entity.CV;
import com.tdmu.entity.User;
import com.tdmu.repository.CVRepo;
import com.tdmu.service.CVService;

@Service
public class CVServiceImpl implements CVService {

	@Autowired
	private CVRepo repo;

	@Override
	public List<CV> findAll() {
		return repo.findAll();
	}

	@Override
	public CV findById(Long id) {
		return repo.findById(id);
	}

	@Override
	@Transactional(rollbackOn = Throwable.class)
	public CV save(CV cv, HttpSession session) throws SQLException {
		User user = (User) session.getAttribute(UserConstant.CURRENT_USER);
		cv.setUser(user);
		cv.setIsDeleted(false);
		cv.setAvatar(
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7RbuAj7zoRZSIDcV_nz2LyZZjwiOETmn7kg&usqp=CAU");
		cv.setImgSrc(
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReN4VJirN7MEPIwKmjuEHuHz_t_6vmX67Sww&usqp=CAU");
		return repo.saveAndFlush(cv);
	}

	@Override
	public List<CV> findBySearch(String textSearch) {
		return repo.findBySearch(textSearch);
	}

	@Override
	public CV deletedCVById(Long id) {
		CV cvResponse = repo.findById(id);
		cvResponse.setIsDeleted(true);
		return repo.saveAndFlush(cvResponse);
	}

	@Override
	public CV updateFullNameById(Long id, String firstName, String lastName) {
		CV cv = repo.findById(id);
		cv.setFirstName(firstName);
		cv.setLastName(lastName);
		return repo.saveAndFlush(cv);
	}

	@Override
	public CV updateViTriUngTuyenById(Long id, String viTriUngTuyen) {
		CV cv = repo.findById(id);
		cv.setViTriUngTuyen(viTriUngTuyen);
		return repo.saveAndFlush(cv);
	}

	@Override
	public CV updateAboutMeById(Long id, String aboutMe) {
		CV cv = repo.findById(id);
		cv.setAbout(aboutMe);
		return repo.saveAndFlush(cv);
	}

	@Override
	public CV updateAddressById(Long id, String address) {
		CV cv = repo.findById(id);
		cv.setAddress(address);
		return repo.saveAndFlush(cv);
	}

	@Override
	public CV updateEmailById(Long id, String email) {
		CV cv = repo.findById(id);
		cv.setEmail(email);
		return repo.saveAndFlush(cv);
	}

	@Override
	public CV updatePhoneById(Long id, String phone) {
		CV cv = repo.findById(id);
		cv.setPhone(phone);
		return repo.saveAndFlush(cv);
	}

	@Override
	public List<CV> findAllCVByUserId(Long userId) {
		return repo.findAllCVByUserId(userId);
	}

}

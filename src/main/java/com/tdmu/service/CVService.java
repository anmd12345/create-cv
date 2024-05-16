package com.tdmu.service;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.tdmu.entity.CV;

public interface CVService {
	List<CV> findAll();

	List<CV> findAllCVByUserId(Long userId);

	CV findById(Long id);

	CV save(CV cv, HttpSession session) throws SQLException;

	List<CV> findBySearch(String textSearch);

	CV deletedCVById(Long id);

	CV updateFullNameById(Long id, String firstName, String lastName);

	CV updateViTriUngTuyenById(Long id, String viTriUngTuyen);

	CV updateAboutMeById(Long id, String aboutMe);

	CV updateAddressById(Long id, String address);

	CV updateEmailById(Long id, String email);

	CV updatePhoneById(Long id, String phone);
}

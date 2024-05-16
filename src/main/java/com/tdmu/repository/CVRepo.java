package com.tdmu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdmu.entity.CV;

@Repository
public interface CVRepo extends JpaRepository<CV, Integer> {
	CV findById(Long id);

	@Query(value = "select * from cv where isDeleted = 0", nativeQuery = true)
	List<CV> findAll();

	@Query(value = "select * from cv where isDeleted = 0 and userId = :userId", nativeQuery = true)
	List<CV> findAllCVByUserId(Long userId);

	@Query(value = "select * from cv where cvName like %:textSearch%", nativeQuery = true)
	List<CV> findBySearch(String textSearch);
}

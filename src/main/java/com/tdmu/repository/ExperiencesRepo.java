package com.tdmu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdmu.entity.Experiences;

@Repository
public interface ExperiencesRepo extends JpaRepository<Experiences, Integer> {
	
	@Query(value = "select * from experiences where cvId = :id", nativeQuery = true)
	List<Experiences> getAllExperiencesById(Long id);
}

package com.tdmu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdmu.entity.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {

	@Query(value = "select * from skill where cvId = :id and isDeleted = 0", nativeQuery = true)
	List<Skill> getAllSkillById(Long id);

	@Query(value = "select * from skill where cvId = :id and isDeleted = 0", nativeQuery = true)
	List<Skill> findAll(Long id);

	Skill findById(Long id);
}

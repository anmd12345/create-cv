package com.tdmu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdmu.entity.Recruit;

@Repository
public interface RecuitRepo extends JpaRepository<Recruit, Integer> {

	@Query(value = "select * from recuit where isDeleted = 0", nativeQuery = true)
	List<Recruit> findAll();

	Recruit findById(Long id);
}

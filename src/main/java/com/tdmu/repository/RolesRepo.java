package com.tdmu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdmu.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {
	Roles findById(Long id);
}

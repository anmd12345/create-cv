package com.tdmu.service;

import java.util.List;

import com.tdmu.entity.Roles;

public interface RolesService {
	List<Roles> findAll();

	Roles findById(Long id);
}

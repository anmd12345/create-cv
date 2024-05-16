package com.tdmu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdmu.entity.Roles;
import com.tdmu.repository.RolesRepo;
import com.tdmu.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesRepo repo;

	@Override
	public List<Roles> findAll() {
		return repo.findAll();
	}

	@Override
	public Roles findById(Long id) {
		return repo.findById(id);
	}
}

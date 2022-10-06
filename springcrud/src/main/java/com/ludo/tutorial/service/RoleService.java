package com.ludo.tutorial.service;

import javax.validation.Valid;

import com.ludo.tutorial.model.Role;

public interface RoleService {

	void save(@Valid Role role);

}

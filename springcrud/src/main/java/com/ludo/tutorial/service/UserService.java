package com.ludo.tutorial.service;

import java.util.List;

import javax.validation.Valid;

import com.ludo.tutorial.model.User;

public interface UserService {

	User getUser(String username);

	User getUserWithBooks(String username);

	void save(@Valid User user);

	void deleteUser(String username);

	List<?> listUsers();

	long numUsers();

	void loanBooks(@Valid User user);

	List<?> ListWithBooks();

}

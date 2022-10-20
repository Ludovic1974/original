package com.ludo.tutorial.service;

import java.util.List;

import com.ludo.tutorial.model.User;

public interface UserService {

	User getUser(String username);

	User getUserWithBooks(String username);

	void save(User user);

	void deleteUser(String username);

	List<?> listUsers();

	List<?> findByEmail(String email);

	List<?> ListWithBooks();

	long numUsers();

	void loanBooks(User user);

}

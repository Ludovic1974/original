package com.ludo.tutorial.dao;

import java.util.List;

import com.ludo.tutorial.model.User;

public interface UserDao extends ObjectDao {

	void delete(String username);

	User get(String username);

	User getWithRoles(String username);

	void loanBooks(User user);

	User getWithBooks(String username);

	List<?> ListWithBooks();

	List<?> findByEmail(Object email);

}

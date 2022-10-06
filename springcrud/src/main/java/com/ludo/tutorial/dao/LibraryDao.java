package com.ludo.tutorial.dao;

public interface LibraryDao extends ObjectDao {

	void delete(long id);

	Object get(long id);
}

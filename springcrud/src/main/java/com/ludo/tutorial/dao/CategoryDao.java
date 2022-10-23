package com.ludo.tutorial.dao;

import java.util.List;

public interface CategoryDao extends LibraryDao {

	List<?> findByName(Object nameValue);
}

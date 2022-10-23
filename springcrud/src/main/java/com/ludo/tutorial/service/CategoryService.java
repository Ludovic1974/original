package com.ludo.tutorial.service;

import java.util.List;

import com.ludo.tutorial.model.Category;

public interface CategoryService {

	List<?> listCategories();

	long numCategories();

	void save(Category category);

	void deleteCategory(long id);

	Category getCategory(long id);

	List<?> findByName(Object nameValue);

}

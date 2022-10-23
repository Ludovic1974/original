package com.ludo.tutorial.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ludo.tutorial.model.Book;
import com.ludo.tutorial.validator.UniqueCategory;

@UniqueCategory(name = "name", id = "id", message = "{category.name.exist}")
public class CategoryDto {

	private long id;

	@Size(max = 100, min = 3, message = "{category.name.invalid}")
	@NotEmpty(message = "{category.value.required}")
	@NotNull(message = "{category.value.notnull}")
	private String name;

	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public CategoryDto() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

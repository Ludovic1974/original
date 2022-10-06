package com.ludo.tutorial.service;

import java.util.List;

import com.ludo.tutorial.model.Book;

public interface BookService {
	List<?> listBooks();

	long numBooks();

	void save(Book book);

	void deleteBook(long id);

	Book getBook(long id);
}

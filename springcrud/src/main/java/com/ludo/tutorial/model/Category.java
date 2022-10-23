package com.ludo.tutorial.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ludo.tutorial.dto.CategoryDto;

@Entity
@Table(name = "category")
public class Category extends DateColumns {

	/*
	 * Propiedades
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	/*
	 * RELACIONES
	 */
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH,
			CascadeType.REMOVE }, mappedBy = "category")
	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	/*
	 * CONSTRUCTORES
	 */

	public Category(long id, Date createdAt, Date updatedAt,
			@Size(max = 100, min = 3, message = "{category.name.invalid}") @NotEmpty(message = "{category.value.required}") String name) {
		super(createdAt, updatedAt);
		this.id = id;
		this.name = name;
	}

	public Category(Date createdAt, Date updatedAt,
			@Size(max = 100, min = 3, message = "{category.name.invalid}") @NotEmpty(message = "{category.value.required}") String name) {
		super(createdAt, updatedAt);
		this.name = name;
	}

	public Category(
			@Size(max = 100, min = 3, message = "{category.name.invalid}") @NotEmpty(message = "{category.value.required}") String name) {

		this.name = name;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * GETTERS / SETTERS
	 */

	public Category(CategoryDto copy) {
		this.setId(copy.getId());
		this.setBooks(copy.getBooks());
		this.setName(copy.getName());
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

	/*
	 * TO STRING
	 */
	@Override
	public String toString() {
		return String.format("Category [getId()=%s, getName()=%s]", getId(), getName());
	}

}

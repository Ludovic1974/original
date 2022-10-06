package com.ludo.tutorial.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book extends DateColumns {

	/*
	 * El hecho de añadir la herencia DateColumns al proyecto hará que el sistema
	 * añadirá nuevas columnas al modelo Book. Eso nos obligará a borrar la tabla
	 * Book de nuestra base de datos.
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	@Size(max = 225, min = 1, message = "{book.title.invalid}")
	@NotEmpty(message = "{book.value.required}")
	private String title;

	@Column(name = "author")
	@Size(max = 225, min = 1, message = "{book.author.invalid}")
	@NotEmpty(message = "{book.value.required}")
	private String author;

	// https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
	// de uno a uno se puede permitir el uso de ALL
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
	@Valid
	private BookDetails bookDetails;

	public BookDetails getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
	}

	@ManyToOne
	@JoinColumn(name = "cat_id")
	@Valid
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "books")
	private List<User> users = new ArrayList<>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Book() {
	}

	/* Importante para la validación de préstamos */
	public Book(String title) {
		this.title = title;
	}

	public Book(Date createdAt, Date updatedAt) {
		super(createdAt, updatedAt);
	}

	public Book(Date createdAt, Date updatedAt, long id,
			@Size(max = 225, min = 1, message = "{book.title.invalid}") @NotEmpty(message = "{book.value.required}") String title,
			@Size(max = 225, min = 1, message = "{book.author.invalid}") @NotEmpty(message = "{book.value.required}") String author) {
		super(createdAt, updatedAt);
		this.id = id;
		this.title = title;
		this.author = author;

	}

	public Book(Date createdAt, Date updatedAt,
			@Size(max = 225, min = 1, message = "{book.title.invalid}") @NotEmpty(message = "{book.value.required}") String title,
			@Size(max = 225, min = 1, message = "{book.author.invalid}") @NotEmpty(message = "{book.value.required}") String author) {
		super(createdAt, updatedAt);
		this.title = title;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return String.format("Book [getId()=%s, getTitle()=%s, getAuthor()=%s]", getId(), getTitle(), getAuthor());
	}

}

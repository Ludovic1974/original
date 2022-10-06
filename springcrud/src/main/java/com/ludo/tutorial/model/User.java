package com.ludo.tutorial.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ludo.tutorial.other.EqualPasswords;

@Entity
@Table(name = "user")
@EqualPasswords(message = "{user.passwords.not.igual}")
public class User extends DateColumns {
	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "name")
	@Size(max = 50, min = 3, message = "{user.name.invalid}")
	private String name;

	@Column(name = "surname")
	@Size(max = 50, min = 3, message = "{user.surname.invalid}")
	private String surname;

	@Column(name = "password")
	@Size(max = 225, min = 3, message = "{user.password.invalid}")
	@NotEmpty(message = "{password.value.required}")
	private String password;

	@Column(name = "confirm_password")
	private String confirmPassword;

	@Column(name = "email", length = 50)
	@Email(message = "{user.email.invalid}")
	// @EmailExist(email = "email") //Probando con el email repetito //no funciona
	private String email;

	@Column(name = "enabled")
	private boolean enabled;

	// https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinTable(name = "user_book", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "book_id") })
	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public User() {
	}

	public User(Date createdAt, Date updatedAt) {
		super(createdAt, updatedAt);

	}

	public User(Date createdAt, Date updatedAt, String username,
			@Size(max = 50, min = 3, message = "{user.name.invalid}") String name,
			@Size(max = 50, min = 3, message = "{user.surname.invalid}") String surname,
			@Size(max = 225, min = 3, message = "{user.password.invalid}") @NotEmpty(message = "{password.value.required}") String password,
			String confirmPassword, @Email(message = "{user.email.invalid}") String email, boolean enabled) {
		super(createdAt, updatedAt);
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.enabled = enabled;
	}

	public User(String username, @Size(max = 50, min = 3, message = "{user.name.invalid}") String name,
			@Size(max = 50, min = 3, message = "{user.surname.invalid}") String surname,
			@Size(max = 225, min = 3, message = "{user.password.invalid}") @NotEmpty(message = "{password.value.required}") String password,
			String confirmPassword, @Email(message = "{user.email.invalid}") String email, boolean enabled) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return String.format(
				"User [getUsername()=%s, getName()=%s, getSurname()=%s, getPassword()=%s, getConfirmPassword()=%s, getEmail()=%s, isEnabled()=%s, getCreatedAt()=%s, getUpdatedAt()=%s]",
				getUsername(), getName(), getSurname(), getPassword(), getConfirmPassword(), getEmail(), isEnabled(),
				getCreatedAt(), getUpdatedAt());
	}

}

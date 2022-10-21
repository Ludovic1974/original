package com.ludo.tutorial.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ludo.tutorial.dto.UserDto;
import com.ludo.tutorial.validator.EqualFields;

@Entity
@Table(name = "user")
@EqualFields(baseField = "password", matchField = "confirmPassword", message = "{user.fields.not.igual}")
public class User extends DateColumns {
	/*
	 * Propiedades
	 */
	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "password")
	private String password;

	@Column(name = "confirm_password")
	private String confirmPassword;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "enabled")
	private boolean enabled;

	/*
	 * RELACIONES
	 */
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

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Role> roles = new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/*
	 * CONSTRUCTORES
	 */

	public User() {
	}

	public User(Date createdAt, Date updatedAt) {
		super(createdAt, updatedAt);

	}

	public User(String username) {
		this.username = username;

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

	public User(UserDto copy) {
		this.setUsername(copy.getUsername());
		this.setName(copy.getName());
		this.setSurname(copy.getSurname());
		this.setEnabled(copy.isEnabled());
		this.setEmail(copy.getEmail());
		this.setPassword(copy.getPassword());
		this.setConfirmPassword(copy.getConfirmPassword());
		this.setBooks(copy.getBooks());
	}

	/*
	 * GETTERS / SETTERS
	 */

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

	/*
	 * TO STRING
	 */
	@Override
	public String toString() {
		return String.format(
				"User [getUsername()=%s, getName()=%s, getSurname()=%s, getPassword()=%s, getConfirmPassword()=%s, getEmail()=%s, isEnabled()=%s, getCreatedAt()=%s, getUpdatedAt()=%s]",
				getUsername(), getName(), getSurname(), getPassword(), getConfirmPassword(), getEmail(), isEnabled(),
				getCreatedAt(), getUpdatedAt());
	}

}

package com.ludo.tutorial.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ludo.tutorial.model.Book;
import com.ludo.tutorial.other.EqualFields;
import com.ludo.tutorial.other.UniqueField;

@EqualFields(baseField = "password", matchField = "confirmPassword", message = "{user.fields.not.igual}")
@UniqueField(email = "email", username = "username", message = "{user.email.exist}")
public class UserDto {

	@NotEmpty(message = "{username.value.required}")
	private String username;

	@Size(max = 50, min = 3, message = "{user.name.invalid}")
	private String name;

	@Size(max = 50, min = 3, message = "{user.surname.invalid}")
	private String surname;

	@Size(max = 225, min = 3, message = "{user.password.invalid}")
	@NotEmpty(message = "{password.value.required}")
	private String password;

	@NotEmpty(message = "{password.value.required}")
	private String confirmPassword;

	@Email(message = "{user.email.invalid}")
	@NotEmpty(message = "{email.value.required}")
	private String email;

	private boolean enabled;

	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public UserDto() {
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
}

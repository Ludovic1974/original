package com.ludo.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "role", indexes = @Index(columnList = "authority", unique = false))
public class Role {
	@Id
	@Column(name = "authority")
	@NotEmpty(message = "{role.value.required}")
	private String authority;

	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role() {

	}

	public Role(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return String.format("Role [getAuthority()=%s]", getAuthority());
	}

}

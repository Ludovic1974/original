package com.ludo.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "authority")
	@NotEmpty(message = "{role.value.required}")
	private String authority;

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
package com.ludo.tutorial.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ludo.tutorial.model.User;
import com.ludo.tutorial.other.Funciones;
import com.ludo.tutorial.service.UserService;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

	String username;
	String email;
	Funciones funciones;

	@Autowired
	private UserService userService; // this is injected to the class

	@Override
	public void initialize(UniqueField constraint) {
		this.username = constraint.username();
		this.email = constraint.email();
		this.funciones = new Funciones();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			boolean valid = false;
			Object emailValue = funciones.getFieldValue(object, this.email);
			List<?> userListFound = userService.findByEmail(emailValue);
			Object usernameValue = funciones.getFieldValue(object, this.username);
			if (!userListFound.isEmpty()) {
				for (Object user : (List<?>) userListFound) {
					if (usernameValue.equals(((User) user).getUsername())) {
						valid = true;
						break;
					}
				}
			} else {
				valid = true;
			}
			return valid;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

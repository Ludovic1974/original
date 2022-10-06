package com.ludo.tutorial.other;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ludo.tutorial.service.UserService;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

	@Autowired
	private UserService userService; // this is injected to the class

	@Override
	public boolean isValid(String email, ConstraintValidatorContext cxt) {
		List<?> userListFound = userService.findByEmail(email);
		if (!userListFound.isEmpty()) {
			return false;
		}
		return true;
	}

}

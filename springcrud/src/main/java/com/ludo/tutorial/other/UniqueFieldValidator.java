package com.ludo.tutorial.other;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ludo.tutorial.model.User;
import com.ludo.tutorial.service.UserService;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

	private String username;
	private String email;

	@Override
	public void initialize(UniqueField constraint) {
		username = constraint.username();
		email = constraint.email();
		System.out.println("Campo Email EN UniqueFieldValidator: " + email);
	}

	@Autowired
	private UserService userService; // this is injected to the class

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			boolean valid = false;
			Object emailValue = getFieldValue(object, this.email);
			List<?> userListFound = userService.findByEmail(emailValue);
			Object usernameValue = getFieldValue(object, this.username);
			System.out.println("Username: " + usernameValue);
			if (!userListFound.isEmpty()) {
				for (User user : (List<User>) userListFound) {
					if (usernameValue.equals(user.getUsername())) {
						valid = true;
						break;
					}
				}
			} else {
				valid = true;
			}

			System.out.println("valid: " + valid);
			return valid;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private Object getFieldValue(Object object, String fieldName) throws Exception {
		Class<?> clazz = object.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

}

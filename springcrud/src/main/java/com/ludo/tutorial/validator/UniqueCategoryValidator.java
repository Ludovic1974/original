package com.ludo.tutorial.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ludo.tutorial.model.Category;
import com.ludo.tutorial.other.Funciones;
import com.ludo.tutorial.service.CategoryService;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, Object> {

	String name;
	String id;
	Funciones funciones;

	@Autowired
	private CategoryService categoryService; // this is injected to the class

	@Override
	public void initialize(UniqueCategory constraint) {
		this.name = constraint.name();
		this.id = constraint.id();
		this.funciones = new Funciones();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			boolean valid = false;
			Object nameValue = funciones.getFieldValue(object, this.name);
			List<?> categoryListFound = categoryService.findByName(nameValue);
			Object idValue = funciones.getFieldValue(object, this.id);
			if (!categoryListFound.isEmpty()) {
				for (Object category : (List<?>) categoryListFound) {
					if (idValue.equals(((Category) category).getId())) {
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

}

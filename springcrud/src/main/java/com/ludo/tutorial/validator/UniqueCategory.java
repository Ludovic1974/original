package com.ludo.tutorial.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCategoryValidator.class)
public @interface UniqueCategory {
	String message() default "{category.name.exist}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String name();

	String id();
}

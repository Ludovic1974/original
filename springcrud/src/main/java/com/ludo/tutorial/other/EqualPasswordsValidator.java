package com.ludo.tutorial.other;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ludo.tutorial.model.User;

/*
* Para escribir nuestra lógica de verificación, debemos crear una clase que
* implemente la interfaz ConstraintValidator. El primer parámetro genérico es
* nuestra anotación y el segundo es la clase de datos anotados.
*/
public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, User> {

	/*
	 * Como ves, en el método isValid() tenemos acceso a una instancia de la clase
	 * validada, por lo tanto podemos comparar dos campos de contraseña.
	 */
	@Override
	public boolean isValid(User form, ConstraintValidatorContext context) {
		if (form.getPassword().equals(form.getConfirmPassword())) {
			System.out.println("contraseña valida");
		} else {
			System.out.println("contraseña invalida");
		}
		return form.getPassword().equals(form.getConfirmPassword());
	}

}

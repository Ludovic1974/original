package com.ludo.tutorial.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ludo.tutorial.other.Funciones;

/*
* Para escribir nuestra lógica de verificación, debemos crear una clase que
* implemente la interfaz ConstraintValidator. El primer parámetro genérico es
* nuestra anotación y el segundo es la clase de datos anotados.
*/
public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {

	/*
	 * Cambio más importante se realiza en esta clase Creamos un par de propiedades
	 * para la clase validadora. Dichas propiedades se almacenan en el método
	 * initialize()
	 */
	private String baseField;
	private String matchField;
	private Funciones funciones;

	@Override
	public void initialize(EqualFields constraint) {
		baseField = constraint.baseField();
		matchField = constraint.matchField();
		funciones = new Funciones();
	}

	/*
	 * Como ves, en el método isValid() tenemos acceso a una instancia de la clase
	 * validada, por lo tanto podemos comparar dos campos de contraseña.
	 */
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			Object baseFieldValue = funciones.getFieldValue(object, baseField);
			Object matchFieldValue = funciones.getFieldValue(object, matchField);
			return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);
		} catch (Exception e) {
			// log error
			return false;
		}
	}

}

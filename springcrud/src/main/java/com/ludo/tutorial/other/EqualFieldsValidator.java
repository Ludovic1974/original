package com.ludo.tutorial.other;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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

	@Override
	public void initialize(EqualFields constraint) {
		baseField = constraint.baseField();
		matchField = constraint.matchField();
	}

	/*
	 * Como ves, en el método isValid() tenemos acceso a una instancia de la clase
	 * validada, por lo tanto podemos comparar dos campos de contraseña.
	 */
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			Object baseFieldValue = getFieldValue(object, baseField);
			Object matchFieldValue = getFieldValue(object, matchField);
			return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);
		} catch (Exception e) {
			// log error
			return false;
		}
	}

	private Object getFieldValue(Object object, String fieldName) throws Exception {
		Class<?> clazz = object.getClass();
		/*
		 * La lógica de validación se volvió más compleja ya que ahora utiliza Reflect
		 * para obtener valores de campos validados.
		 */
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	/*
	 * La solución no es perfecta ya que depende del mecanismo de reflexión. Si se
	 * cambia el nombre de un campo en la clase de datos, el atributo de cadena
	 * correspondiente en la anotación de restricción declarada también tiene que
	 * ser alterado. El compilador no advertirá sobre la discrepancia, por lo tanto
	 * se requiere más atención de un programador.
	 */

}

package com.ludo.tutorial.other;

import java.lang.reflect.Field;

public class Funciones {

	public Funciones() {
	}

	public Object getFieldValue(Object object, String fieldName) throws Exception {
		Class<?> clazz = object.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}
}
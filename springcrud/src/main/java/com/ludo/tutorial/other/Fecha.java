package com.ludo.tutorial.other;

import java.util.Calendar;
import java.util.Date;

public class Fecha {

	public static Date getTimeStamp() {
		Calendar cal = Calendar.getInstance();
		Date dateRepresentation = cal.getTime();
		return dateRepresentation;
	}
}

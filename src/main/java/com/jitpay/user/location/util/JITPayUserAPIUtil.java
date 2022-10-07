package com.jitpay.user.location.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JITPayUserAPIUtil {

	public static LocalDateTime convertStringToLocalDateTime(String dateTime) throws DateTimeParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime fromDateT = LocalDateTime.parse(dateTime, formatter);
		return fromDateT;
	}
	
	
}

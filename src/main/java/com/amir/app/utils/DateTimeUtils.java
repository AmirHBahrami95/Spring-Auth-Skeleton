package com.amir.app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
	private static final DateTimeFormatter formatter = 
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	// 2024-12-18 05:30:14
	public static LocalDateTime parseDateStr(String str) {
		return LocalDateTime.parse(str, formatter);
	}
	
	public static String toDateStr(LocalDateTime ldt) {
		return ldt.format(formatter);
	}
}

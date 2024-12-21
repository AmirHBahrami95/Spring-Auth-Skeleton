package com.amir.app.utils;

import java.util.HexFormat;

import org.apache.commons.lang3.RandomStringUtils;

public class StrUtils {
	
	public static String toHex(String str) {
		return HexFormat.of().formatHex(str.getBytes());
	}
	
	public static String generateSecureRandom(int nChars) {
		return RandomStringUtils.secure().next(nChars);
	}
	
}

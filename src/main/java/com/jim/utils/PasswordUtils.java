package com.jim.utils;

import java.util.regex.Pattern;

/**
 * Created by jim on 2017/7/26.
 * This class is ...
 */
public class PasswordUtils {
	public static int checkPassword(String password) {
		int strong = 10, length = 8;
		Pattern pattern = Pattern.compile("[\\w]+");

		if (password.length() < length) {
			strong--;
		}

		if (!pattern.matcher(password).matches()) {
			strong--;
		}

		pattern = Pattern.compile("[^\\d|^\\w|\\s]+");
		if (!pattern.matcher(password).matches()) {
			strong--;
		}

		pattern = Pattern.compile("[A-Z]+");
		if (!pattern.matcher(password).matches()) {
			strong--;
		}
		return strong;
	}
}

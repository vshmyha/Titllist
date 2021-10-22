package com.lerkin.titllist.tool;

public interface StringUtils {

	static boolean isEmpty(String s) {

		return s == null || s.trim().isEmpty();
	}

	static boolean isNotEmpty(String s) {

		return !isEmpty(s);
	}
}

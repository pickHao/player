package com.performer.player.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static boolean isNullOrSpace(String str) {
		if (str != null && !str.equals("")) {
			Pattern p1 = Pattern.compile("^[\\s　]+|[\\s　]+$");
			Matcher m1 = p1.matcher(str);
			String checkMoziretu = m1.replaceAll("");
			boolean returnValue = "".equals(checkMoziretu);
			return returnValue;
		} else {
			return true;
		}
	}
}

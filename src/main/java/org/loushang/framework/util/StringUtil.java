package org.loushang.framework.util;

import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {

	public static boolean isNotEmpty(Object str) {
		return !isEmpty(str);
	}
	
}
package com.rose4j.biyebao.web.velocity;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.tools.config.DefaultKey;

@DefaultKey("StringTool")
public class StringTool {

	public static boolean isNotEmpty(String str) {
		return StringUtils.isNotEmpty(str);
	}
}

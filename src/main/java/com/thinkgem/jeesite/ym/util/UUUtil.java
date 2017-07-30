package com.thinkgem.jeesite.ym.util;

import java.util.UUID;

public class UUUtil {

	public static String getUUId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}

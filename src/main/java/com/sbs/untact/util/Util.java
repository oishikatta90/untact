package com.sbs.untact.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getNowDateStr() {
		Date time = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		return format1.format(time);
	}
}

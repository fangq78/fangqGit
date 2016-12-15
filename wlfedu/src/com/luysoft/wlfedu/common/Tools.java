package com.luysoft.wlfedu.common;

import java.text.NumberFormat;

public class Tools {
	public static String formatString(Object obj,int digit) {
		NumberFormat formatter = NumberFormat.getNumberInstance(); 
		formatter.setMinimumIntegerDigits(digit);     
		formatter.setGroupingUsed(false);  
		return formatter.format(obj);   
	}
}

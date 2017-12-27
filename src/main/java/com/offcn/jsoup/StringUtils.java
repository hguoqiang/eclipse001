package com.offcn.jsoup;

public class StringUtils {
	
	
	
	

	public static String strSpilt(String str){
		String[] split = (str.trim()).split(",");

		String newStr = "";
		for (String string : split) {
			newStr += string;
		}
		return newStr;
	}
}

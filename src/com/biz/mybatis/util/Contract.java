package com.biz.mybatis.util;

import java.util.Map;
import java.util.TreeMap;

public class Contract {
	
	public static Map<String,String> SubList = null;
	
	static {
		SubList = new TreeMap<String,String>();
		SubList.put("1", "국어");
		SubList.put("2", "영어");
		SubList.put("3", "수학");
		SubList.put("4", "과학");
		SubList.put("5", "음악");
		SubList.put("6", "미술");
		SubList.put("7", "국사");
	}
	
	
	
}

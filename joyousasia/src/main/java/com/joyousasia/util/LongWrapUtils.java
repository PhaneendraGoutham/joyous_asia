package com.joyousasia.util;

import org.apache.commons.lang3.StringUtils;



public class LongWrapUtils {
	
	public static void main(String args[]) {
		String str = "100001111111111111111";


		System.out.println(LongtoWrap(str));
	}
	
	public static String LongtoWrap(String sourceStr) {
		
		
			
			if(StringUtils.isEmpty(sourceStr)) return null;
			
			StringBuilder str = new StringBuilder(sourceStr);
			int idx =str.length();
			
			while (idx>0)
			{
				str.insert(idx, "<wbr>");
				idx-=1;
			}
			
			return str.toString();
		}
		
	
}

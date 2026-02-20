package com.maylin.util;

public class StringUtil {
	
	public static String formatName(String name) {
		
		 if(name==null ||  name.isBlank()) return name;
		 name=name.trim();
		 return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
		 
	}
	
	public static String formatLastName(String lastName) {
		
		if(lastName==null || lastName.isBlank()) return lastName;
		
		lastName=lastName.trim();
		return lastName.toUpperCase();
	}
	
	public static String formatEmail(String email) {
		if(email==null || email.isBlank()) return email;
		email=email.trim();
		return email.toLowerCase();
	
	}
	
}

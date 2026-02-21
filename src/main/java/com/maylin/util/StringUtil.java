package com.maylin.util;

public class StringUtil {
	
	public static String formatName(String name) {
		
		 if(name==null ||  name.isBlank()) throw new RuntimeException("Name cannot be blank!");
		 name=name.trim();                 //TODO:Özel exception yaz 
		 return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
		 
	}
	
	public static String formatLastName(String lastName) {
		
		if(lastName==null || lastName.isBlank()) throw new RuntimeException("Lastname cannot be blank!");
		
		lastName=lastName.trim();
		return lastName.toUpperCase();
	}
	
	public static String formatEmail(String email) {
		if(email==null || email.isBlank()) throw new RuntimeException("Email cannot be blank!");
		email=email.trim();
		return email.toLowerCase();
	
	}
	
}

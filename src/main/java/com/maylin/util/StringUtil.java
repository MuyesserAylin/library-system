package com.maylin.util;

public class StringUtil {
	
	public static String formatName(String name) {
		
		 name=name.trim();              
		 return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
		 
	}
	
	public static String formatLastName(String lastName) {
		
		lastName=lastName.trim();
		return lastName.toUpperCase();
	}
	
	public static String formatEmail(String email) {
		
		email=email.trim();
		return email.toLowerCase();
	
	}
	
	public static String formatISBN(String isbn) {
	    return isbn.trim().toUpperCase();
	}
	
	public static String formatTitle(String title) {
	    
	    title = title.trim();
	    String[] words = title.split(" ");
	    StringBuilder result = new StringBuilder();
	    
	    for(String word : words) {
	        if(!word.isEmpty()) {
	            result.append(word.substring(0,1).toUpperCase())
	                  .append(word.substring(1).toLowerCase())
	                  .append(" ");
	        }
	    }
	    
	    return result.toString().trim();
	}
	
	public static String formatBarcode(String barcode) {
		return barcode.trim();
	}
	
}

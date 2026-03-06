package com.maylin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	
    MEMBER_NOT_FOUND(1001, "Member not found."),
    EMAIL_ALREADY_EXISTS(1002, "Email address already in use."),
    MEMBER_HAS_ACTIVE_LOAN(1003, "Member has active loans, cannot be deleted."),

  
    AUTHOR_NOT_FOUND(2001, "Author not found."),
    AUTHOR_ALREADY_EXISTS(2002, "Author already exists."),
    AUTHOR_HAS_BOOKS(2003, "Author has books, cannot be deleted."),

 
    CATEGORY_NOT_FOUND(3001, "Category not found."),
    CATEGORY_ALREADY_EXISTS(3002, "Category already exists."),
    CATEGORY_HAS_BOOKS(3003, "Category has books, cannot be deleted."),
    
    
    
    BOOK_NOT_FOUND(4001,"Book not found."),
    BOOK_ALREADY_EXISTS(4002,"Book already exists"),
    BOOK_HAS_ACTIVE_LOAN(4003,"Book has active bookitem,cannot be deleted"),
    BOOK_MUST_HAVE_CATEGORY(4004, "Book must have at least one category."),
    
    BOOKITEM_NOT_FOUND(5001, "Bookitem not found."),
    BOOKITEM_ALREADY_EXISTS(5002, "Bookitem already exists."),
    BOOKITEM_HAS_ACTIVE_LOAN(5003, "Bookitem has active loan,cannot be deleted."),
    
    VALIDATION_ERROR(9001, "Validation failed."),
    INTERNAL_ERROR(9999, "An unexpected error occurred."),
    HTTP_MESSAGE_NOT_READABLE(9002, "Request body is missing or malformed.");

	
	private final int errorCode;
	
	private final String errorMessage;

}

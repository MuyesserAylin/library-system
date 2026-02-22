package com.maylin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	// Member
    MEMBER_NOT_FOUND(1001, "Member not found."),
    EMAIL_ALREADY_EXISTS(1002, "Email address already in use."),
    MEMBER_HAS_ACTIVE_LOAN(1003, "Member has active loans, cannot be deleted."),

    // Author
    AUTHOR_NOT_FOUND(2001, "Author not found."),
    AUTHOR_ALREADY_EXISTS(2002, "Author already exists."),
    AUTHOR_HAS_BOOKS(2003, "Author has books, cannot be deleted."),

    // Category
    CATEGORY_NOT_FOUND(3001, "Category not found."),
    CATEGORY_ALREADY_EXISTS(3002, "Category already exists."),
    CATEGORY_HAS_BOOKS(3003, "Category has books, cannot be deleted.");

	
	private final int errorCode;
	
	private final String errorMessage;

}

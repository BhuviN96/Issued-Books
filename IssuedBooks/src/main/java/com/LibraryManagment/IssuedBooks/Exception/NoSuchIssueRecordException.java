package com.LibraryManagment.IssuedBooks.Exception;

public class NoSuchIssueRecordException extends RuntimeException {

    public NoSuchIssueRecordException(String message) {
        super(message);
    }
}

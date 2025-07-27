package com.LibraryManagment.IssuedBooks.Controller;

import com.LibraryManagment.IssuedBooks.Exception.CustomException;
import com.LibraryManagment.IssuedBooks.Exception.NoSuchIssueRecordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IssuedBooksControllerAdvice {
    @ExceptionHandler(NoSuchIssueRecordException.class)
    public ResponseEntity<CustomException> handleNoSuchIssueRecordException(NoSuchIssueRecordException e) {
        CustomException exception = new CustomException(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception);
    }

}

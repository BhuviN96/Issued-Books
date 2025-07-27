package com.LibraryManagment.IssuedBooks.Mapper;

import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;

import java.util.List;

public class IssuedBookToIssuedBookGetDTO {
    public static IssuedBooksGetDTO mapToIssuedBooksGetDTO(IssuedBooks issuedBooksData, BookDTO issuedBooks) {
        return new IssuedBooksGetDTO(issuedBooks,issuedBooksData.getIssueDate(),issuedBooksData.getReturnDate(),issuedBooksData.isReturned());
    }
}

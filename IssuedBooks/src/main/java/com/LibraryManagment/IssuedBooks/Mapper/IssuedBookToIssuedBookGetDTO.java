package com.LibraryManagment.IssuedBooks.Mapper;

import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;

import java.util.List;

public class IssuedBookToIssuedBookGetDTO {
    public static IssuedBooksGetDTO mapToIssuedBooksGetDTO(IssuedBooks issuedBooksData, BookDTO issuedBooks) {

        IssuedBooksGetDTO issuedBooksGetDTO = new IssuedBooksGetDTO();
        issuedBooksGetDTO.setBook(issuedBooks);
        issuedBooksGetDTO.setIssueDate(issuedBooksData.getIssueDate());
        issuedBooksGetDTO.setReturnDate(issuedBooksData.getReturnDate());
        issuedBooksGetDTO.setReturned(issuedBooksData.isReturned());
        return issuedBooksGetDTO;
    }
}

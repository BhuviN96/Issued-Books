package com.LibraryManagment.IssuedBooks.DTO;



import java.time.LocalDate;
import java.util.List;

public class IssuedBooksGetDTO {
    private BookDTO book;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public IssuedBooksGetDTO(BookDTO book, LocalDate issueDate, LocalDate returnDate, boolean isReturned) {
        this.book = book;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}

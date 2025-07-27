package com.LibraryManagment.IssuedBooks.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
public class IssuedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "issue_seq")
    @SequenceGenerator(name = "issue_seq", sequenceName = "issue_sequence", initialValue = 1000, allocationSize = 1)
    private Long issueId;
    @NotNull
    private Long bookId;
    @NotNull
    private Long userId;
    @NotNull
    private LocalDate issueDate;
    private LocalDate returnDate;
    @JsonProperty(value = "isReturned")
    private Boolean isReturned;

    public IssuedBooks() {
    }
    public IssuedBooks(Long bookId, Long userId, LocalDate issueDate, LocalDate returnDate, Boolean isReturned) {
        this.bookId = bookId;
        this.userId = userId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    @PrePersist
    public void prePersist() {
        if (issueDate == null) {
            issueDate = LocalDate.now();
        }
        if (returnDate == null) {
            returnDate = issueDate.plusDays(14);
        }
    }
    @PreUpdate
    public void preUpdate() {
        if (issueDate == null) {
            issueDate = LocalDate.now();
        }
        if (returnDate == null) {
            returnDate = issueDate.plusDays(14);
        }
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean isReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "IssuedBooks{" +
                "issueId=" + issueId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                ", isReturned=" + isReturned +
                '}';
    }
}

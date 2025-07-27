package com.LibraryManagment.IssuedBooks.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}

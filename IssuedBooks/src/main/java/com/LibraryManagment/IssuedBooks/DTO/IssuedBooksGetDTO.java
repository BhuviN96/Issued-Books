package com.LibraryManagment.IssuedBooks.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBooksGetDTO {
    private BookDTO book;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private boolean isReturned;
}

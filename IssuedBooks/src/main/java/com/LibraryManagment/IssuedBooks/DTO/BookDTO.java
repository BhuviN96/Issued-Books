package com.LibraryManagment.IssuedBooks.DTO;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String name;
    private String description;
    private String author;
    private String publisher;
    private LocalDate publishedDate;
}

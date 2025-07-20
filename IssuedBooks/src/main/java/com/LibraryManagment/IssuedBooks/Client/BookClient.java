package com.LibraryManagment.IssuedBooks.Client;

import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", url = "http://localhost:8080") // Adjust the URL as needed
public interface  BookClient {
     @GetMapping("/book/{id}")
     BookDTO getBookById(@PathVariable("id") Long id);
}

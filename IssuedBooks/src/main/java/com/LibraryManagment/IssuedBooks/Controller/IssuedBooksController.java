package com.LibraryManagment.IssuedBooks.Controller;

import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;
import com.LibraryManagment.IssuedBooks.Service.IssuedBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class IssuedBooksController{

    // Example methods could include:
    // - issueBook(Long bookId, Long userId)
    // - returnBook(Long issueId)
    @Autowired
    private IssuedBookService issuedBooksService;

    @PostMapping("/issueBook")
    public void issueBook(@RequestBody IssuedBooks issuedBook){
        issuedBooksService.issueBook(issuedBook);
    }

    @GetMapping("/getIssuedBookById/{userId}")
    public List<IssuedBooksGetDTO> getIssuedBookById(@PathVariable Long userId){
        return issuedBooksService.getIssuedBookById(userId);
    }

}

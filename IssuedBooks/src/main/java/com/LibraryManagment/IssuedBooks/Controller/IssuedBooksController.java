package com.LibraryManagment.IssuedBooks.Controller;

import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Exception.NoSuchIssueRecordException;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;
import com.LibraryManagment.IssuedBooks.Service.IssuedBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class IssuedBooksController{

    @Autowired
    private IssuedBookService issuedBooksService;

    @PostMapping("/issueBook")
    public ResponseEntity<Object> issueBook(@RequestBody IssuedBooks issuedBook){
        System.out.println("Issuing Book: " + issuedBook.getUserId()+" " + issuedBook.getBookId());
        if(issuedBook.getUserId()==null && issuedBook.getBookId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID and Book ID must not be null");
        }else {
            issuedBooksService.issueBook(issuedBook);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @GetMapping("/getIssuedBookById/{userId}")
    public ResponseEntity<List<IssuedBooksGetDTO>> getIssuedBookById(@PathVariable Long userId){
        List<IssuedBooksGetDTO> issuedBooks = issuedBooksService.getIssuedBookById(userId);
        if (issuedBooks.isEmpty()) {
            throw new NoSuchIssueRecordException("No Issued Book Present with userId: " + userId);
        }else {
            return ResponseEntity.ok().body(issuedBooks);
        }
    }

    @PatchMapping("/patchIssuedBook/userId/{userId}/bookId/{bookId}")
    public ResponseEntity<Object> patchIssuedBook(@PathVariable Long userId, @PathVariable Long bookId,@RequestBody IssuedBooks issuedBook) {
        issuedBooksService.patchIssuedBook(userId,bookId,issuedBook);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/deleteIssuedBook/userId/{userId}/bookId/{bookId}")
    public ResponseEntity<Object> deleteIssuedBook(@PathVariable Long userId, @PathVariable Long bookId) {
        issuedBooksService.deleteIssuedBook(userId, bookId);
        return ResponseEntity.noContent().build();
    }
}

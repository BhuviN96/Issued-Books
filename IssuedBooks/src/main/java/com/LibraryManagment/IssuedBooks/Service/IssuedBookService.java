package com.LibraryManagment.IssuedBooks.Service;

import com.LibraryManagment.IssuedBooks.Client.BookClient;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Mapper.IssuedBookToIssuedBookGetDTO;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;
import com.LibraryManagment.IssuedBooks.Repository.IssuedBooksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IssuedBookService {
    @Autowired
    private IssuedBooksRepository issuedBooksRepository;
    @Autowired
    private BookClient bookClient;

    public void issueBook(IssuedBooks issuedBooks) {
        issuedBooksRepository.save(issuedBooks);
    }

    public List<IssuedBooksGetDTO> getIssuedBookById(Long userId) {
        List<IssuedBooks> issuedBooks = issuedBooksRepository.findByUserId(userId);
        System.out.println("Issued Books: " + issuedBooks);
        List<IssuedBooksGetDTO> books =  issuedBooks.stream()
                .map(issuedBook -> IssuedBookToIssuedBookGetDTO.
                        mapToIssuedBooksGetDTO(issuedBook,
                                bookClient.getBookById(issuedBook.getUserId()))).toList();
        return books;
    }
}

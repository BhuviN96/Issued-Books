package com.LibraryManagment.IssuedBooks.Service;

import com.LibraryManagment.IssuedBooks.Client.BookClient;
import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Exception.NoSuchIssueRecordException;
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
        if(issuedBooks == null) {
            throw new IllegalArgumentException("IssuedBooks cannot be null");
        }
        issuedBooksRepository.save(issuedBooks);
    }

    public List<IssuedBooksGetDTO> getIssuedBookById(Long userId) {
        if(userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        List<IssuedBooks> issuedBooks = issuedBooksRepository.findByUserId(userId);
        System.out.println("Issued Books: " + issuedBooks);
        List<IssuedBooksGetDTO> books =  issuedBooks.stream()
                .map(issuedBook -> IssuedBookToIssuedBookGetDTO.
                        mapToIssuedBooksGetDTO(issuedBook,
                                bookClient.getBookById(issuedBook.getBookId()))).toList();
        return  books;
    }

    public void patchIssuedBook(Long userId, Long bookId,IssuedBooks issuedBookUpdate) {
        System.out.println("Updating Issued Book with userId: " + userId + " and bookId: " + bookId);
        IssuedBooks issuedBook = issuedBooksRepository.findByUserIdAndBookId(userId, bookId);
        System.out.println("Found Issued Book: " + issuedBook);
        if (issuedBook == null) {
            throw new RuntimeException("No Issued Book Present with userId: " + userId + " and bookId: " + bookId);
        }else{
            if(issuedBookUpdate.getIssueDate() != null) {
                System.out.println("Updating issue date to: " + issuedBookUpdate.getIssueDate());
                issuedBook.setIssueDate(issuedBookUpdate.getIssueDate());
            }
            if(issuedBookUpdate.getReturnDate() != null) {
                System.out.println("Updating return date to: " + issuedBookUpdate.getReturnDate());
                issuedBook.setReturnDate(issuedBookUpdate.getReturnDate());
            }
            if (issuedBookUpdate.getIsReturned() != null) {
                System.out.println("Updating returned status to: " + issuedBookUpdate.getIsReturned());
                issuedBook.setIsReturned(issuedBookUpdate.getIsReturned());
            }
        }
        System.out.println("Saving updated Issued Book: " + issuedBook);
        issuedBooksRepository.save(issuedBook);
    }

    public void deleteIssuedBook(Long userId, Long bookId) {
        System.out.println("Deleting Issued Book with userId: " + userId + " and bookId: " + bookId);
        IssuedBooks issuedBook = issuedBooksRepository.findByUserIdAndBookId(userId, bookId);
        if (issuedBook == null) {
            throw new NoSuchIssueRecordException("No Issued Book Present with userId: " + userId + " and bookId: " + bookId);
        }
        issuedBooksRepository.delete(issuedBook);
        System.out.println("Deleted Issued Book: " + issuedBook);
    }
}

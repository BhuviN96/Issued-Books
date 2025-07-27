package com.LibraryManagment.IssuedBooks.Service;

import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;
import com.LibraryManagment.IssuedBooks.Repository.IssuedBooksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class IssuedBookServiceTest {

    @Mock
    private IssuedBooksRepository issuedBooksRepository;

    @InjectMocks
    private IssuedBookService issuedBookService;

    @Mock
    private IssuedBooks issuedBooks;

    @Test
    public void testIssueBook() {
        issuedBookService.issueBook(issuedBooks);
        verify(issuedBooksRepository, times(1)).save(issuedBooks);
    }

}
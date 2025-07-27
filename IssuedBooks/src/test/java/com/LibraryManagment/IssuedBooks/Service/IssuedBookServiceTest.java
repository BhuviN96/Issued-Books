package com.LibraryManagment.IssuedBooks.Service;

import com.LibraryManagment.IssuedBooks.Client.BookClient;
import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Exception.NoSuchIssueRecordException;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;
import com.LibraryManagment.IssuedBooks.Repository.IssuedBooksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class IssuedBookServiceTest {

    @Mock
    private IssuedBooksRepository issuedBooksRepository;
    @Mock
    private BookClient bookClient;

    @InjectMocks
    private IssuedBookService issuedBookService;

    @Mock
    private IssuedBooks issuedBooks;

    @Test
    public void testIssueBook() {
        issuedBookService.issueBook(issuedBooks);
        verify(issuedBooksRepository, times(1)).save(issuedBooks);
    }
    @Test
    public void testIssueBookNull() {
        assertThrows(IllegalArgumentException.class, () -> issuedBookService.issueBook(null));
    }
    @Test
    public void testGetIssuedBookById() {
        List<IssuedBooks> issuedBooksList = List.of(
                new IssuedBooks(1L, 1L,1L, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15), false)
        );
        BookDTO book = new BookDTO("Book","Book Description","author","publisher",LocalDate.now());
        List<IssuedBooksGetDTO> issuedBooksGetDTOList = List.of(
                new IssuedBooksGetDTO(book, LocalDate.of(2023, 10, 1),
                        LocalDate.of(2023, 10, 15), false)
        );
        when(issuedBooksRepository.findByUserId(anyLong())).thenReturn(issuedBooksList);
        when(bookClient.getBookById(anyLong())).thenReturn(book);
        List<IssuedBooksGetDTO> result = issuedBookService.getIssuedBookById(anyLong());
        verify(issuedBooksRepository, times(1)).findByUserId(anyLong());
        verify(bookClient, times(1)).getBookById(anyLong());
        System.out.println("result: " + result);
        assertEquals(issuedBooksGetDTOList, result);
    }
    @Test
    public void testGetIssuedBookByIdNull() {
        assertThrows(IllegalArgumentException.class, () -> issuedBookService.getIssuedBookById(null));
    }

    @Test
    public void testPatchIssuedBookAllValues() {
        when(issuedBooksRepository.findByUserIdAndBookId(anyLong(), anyLong()))
                .thenReturn(new IssuedBooks(1L, 1L, 1L, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15), false));
        IssuedBooks issuedBookUpdate = new IssuedBooks(1L,1L,1L,LocalDate.now().minusDays(14), LocalDate.now(),true);
        issuedBookService.patchIssuedBook(1L, 1L, issuedBookUpdate);
        verify(issuedBooksRepository, times(1)).findByUserIdAndBookId(anyLong(), anyLong());
        verify(issuedBooksRepository, times(1)).save(any(IssuedBooks.class));
        assertEquals(LocalDate.now(), issuedBookUpdate.getReturnDate());
        assertEquals(LocalDate.now().minusDays(14), issuedBookUpdate.getIssueDate());
        assertEquals(true, issuedBookUpdate.getIsReturned());
    }
    @Test
    public void testPatchIssuedNullBook() {
        when(issuedBooksRepository.findByUserIdAndBookId(anyLong(), anyLong()))
                .thenReturn(null);
        assertThrows(RuntimeException.class,() -> issuedBookService.patchIssuedBook(1L, 1L, new IssuedBooks()));
    }
    @Test
    public void testDeleteIssuedBook() {
        when(issuedBooksRepository.findByUserIdAndBookId(anyLong(), anyLong()))
                .thenReturn(new IssuedBooks(1L, 1L, 1L, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15), false));
        issuedBookService.deleteIssuedBook(1L, 1L);
        verify(issuedBooksRepository, times(1)).findByUserIdAndBookId(anyLong(), anyLong());
        verify(issuedBooksRepository, times(1)).delete(any(IssuedBooks.class));
    }
    @Test
    public void testDeleteIssuedBookNotFound() {
        when(issuedBooksRepository.findByUserIdAndBookId(anyLong(), anyLong()))
                .thenReturn(null);
        assertThrows(NoSuchIssueRecordException.class, () -> issuedBookService.deleteIssuedBook(1L, 1L));
        verify(issuedBooksRepository, times(1)).findByUserIdAndBookId(anyLong(), anyLong());
    }
}
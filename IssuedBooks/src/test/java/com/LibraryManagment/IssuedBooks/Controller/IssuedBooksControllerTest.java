package com.LibraryManagment.IssuedBooks.Controller;


import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.DTO.IssuedBooksGetDTO;
import com.LibraryManagment.IssuedBooks.Exception.NoSuchIssueRecordException;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;
import com.LibraryManagment.IssuedBooks.Service.IssuedBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IssuedBooksControllerTest {

    @Mock
    private IssuedBookService issuedBookService;

    @Mock
    IssuedBooks issuedBook;

    @InjectMocks
    private IssuedBooksController issuedBooksController;


    @Test
    public void testIssueBookController() {
        when(issuedBook.getUserId()).thenReturn(1L);
        when(issuedBook.getBookId()).thenReturn(1L);
        ResponseEntity<Object> response = issuedBooksController.issueBook(issuedBook);
        verify(issuedBookService, times(1)).issueBook(issuedBook);
        assertEquals("201 CREATED", response.getStatusCode().toString());
    }

    @Test
    public void testIssueBookControllerWithNullUserIdAndBookId() {
        when(issuedBook.getUserId()).thenReturn(null);
        when(issuedBook.getBookId()).thenReturn(null);
        ResponseEntity<Object> response = issuedBooksController.issueBook(issuedBook);
        verify(issuedBookService, never()).issueBook(issuedBook);
        assertEquals("400 BAD_REQUEST", response.getStatusCode().toString());
    }
    @Test
    public void testGetIssuedBookById(){
        List<IssuedBooksGetDTO> issuedBooksGetDTOList = List.of(
                new IssuedBooksGetDTO(new BookDTO("Book1 ", "Head First Java", "Kathy Sierra", "Programming",
                        LocalDate.now().minusDays(10)), LocalDate.now().minusDays(5), LocalDate.now().plusDays(9), false),
                new IssuedBooksGetDTO(new BookDTO("Book2", "Design Patterns", "GoF", "Architecture",LocalDate.now().plusDays(10)),
                        LocalDate.now().minusDays(10), LocalDate.now().plusDays(4), true));
        when(issuedBookService.getIssuedBookById(anyLong())).thenReturn(issuedBooksGetDTOList);
        ResponseEntity<List<IssuedBooksGetDTO>> response = issuedBooksController.getIssuedBookById(anyLong());
        verify(issuedBookService,times(1)).getIssuedBookById(anyLong());
        assertEquals("200 OK", response.getStatusCode().toString());
        System.out.println(response.getBody());
        assertEquals(issuedBooksGetDTOList, response.getBody());

    }
    @Test
    public void testGetIssuedBookByIdNo(){
        when(issuedBookService.getIssuedBookById(anyLong())).thenReturn(List.of());
        assertThrows(NoSuchIssueRecordException.class,() ->{
            issuedBooksController.getIssuedBookById(anyLong());
        });
    }
    @Test
    public void testPatchIssuedBook() {
        issuedBooksController.patchIssuedBook(1L, 1L, issuedBook);
        verify(issuedBookService, times(1)).patchIssuedBook(1L, 1L, issuedBook);
    }
    @Test
    public void testDeleteIssuedBook() {
        issuedBooksController.deleteIssuedBook(1L, 1L);
        verify(issuedBookService, times(1)).deleteIssuedBook(1L, 1L);
    }
}

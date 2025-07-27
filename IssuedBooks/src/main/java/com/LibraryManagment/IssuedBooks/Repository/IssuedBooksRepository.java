package com.LibraryManagment.IssuedBooks.Repository;

import com.LibraryManagment.IssuedBooks.DTO.BookDTO;
import com.LibraryManagment.IssuedBooks.Model.IssuedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedBooksRepository extends JpaRepository<IssuedBooks,Long> {

    List<IssuedBooks> findByUserId(Long userId);
    IssuedBooks findByUserIdAndBookId(Long userId, Long bookId);
}

package net.bookstores.assignment.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.bookstores.assignment.entities.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b ORDER BY b.bookId DESC")
    List<Book> findTop5Books(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findByTitleContaining(@Param("title") String title);

    @Query("SELECT b FROM Book b ORDER BY FUNCTION('NEWID')")
    List<Book> find10RandomBooks(Pageable pageable);

    @Query("SELECT b FROM Book b ORDER BY b.bookId DESC")
    List<Book> danhSachGiamDan();
}

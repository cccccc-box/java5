package net.bookstores.assignment.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.bookstores.assignment.entities.Author;

public interface AuthorDao extends JpaRepository<Author, Integer> {
    @Query("SELECT a FROM Author a ORDER BY FUNCTION('NEWID')")
    List<Author> findRandom5Authors(Pageable pageable);

}

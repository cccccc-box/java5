package net.bookstores.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import net.bookstores.assignment.dao.AuthorDao;
import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.entities.Author;
import net.bookstores.assignment.entities.Book;

@Service
public class HomeService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    public List<Book> getTop5Books() {
        Pageable topFive = PageRequest.of(0, 5);
        return bookDao.findTop5Books(topFive);
    }

    public List<Book> getTop10RandomBooks() {
        Pageable topTen = PageRequest.of(0, 10);
        return bookDao.find10RandomBooks(topTen);
    }

    public List<Author> getTop5RandomAuthors() {
        Pageable topFiveAuthor = PageRequest.of(0, 5);
        return authorDao.findRandom5Authors(topFiveAuthor);
    }
}

package net.bookstores.assignment.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import net.bookstores.assignment.dao.BookDao;

import net.bookstores.assignment.entities.Book;

@Service
public class BookDetailService {
    @Autowired
    BookDao bookDao;

    public Optional<Book> getBookById(Integer id) {
        return bookDao.findById(id);
    }
}

package net.bookstores.assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.entities.Book;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public Book detail(Integer id) {
        Optional<Book> book = bookDao.findById(id);
        return book.get();
    }

}

package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.Book;
import com.timskiproekt.pagepicks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByISBN(String ISBN) {
        return bookRepository.findById(ISBN);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(String ISBN) {
        bookRepository.deleteById(ISBN);
    }

}

package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.Book;
import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.User;
import com.timskiproekt.pagepicks.repository.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStatusService {
    @Autowired
    private BookStatusRepository bookStatusRepository;

    public List<BookStatus> findByUser(User user) {
        return bookStatusRepository.findByUser(user);
    }

    public BookStatus saveBookStatus(BookStatus bookStatus) {
        return bookStatusRepository.save(bookStatus);
    }

    public BookStatus updateCurrentPage(User user, Book book, int currentPage) {
        BookStatus bookStatus = bookStatusRepository.findByUserAndBook(user, book);
        if (bookStatus != null && bookStatus.getStatus().equals("READING")){
            bookStatus.setCurrentPage(currentPage);
            return bookStatusRepository.save(bookStatus);
        }
        return null;
    }
}

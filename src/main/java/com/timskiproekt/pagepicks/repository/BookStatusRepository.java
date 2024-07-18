package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.User;
import com.timskiproekt.pagepicks.model.BookStatusEnum;

import java.util.List;


public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    List<BookStatus> findByUser(User user);
    List<BookStatus> findByUserAndStatus(User user, BookStatusEnum status);
    BookStatus findByUserAndBook(User user, Book book);
}

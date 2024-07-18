package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.model.Book;
import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.User;
import com.timskiproekt.pagepicks.service.BookService;
import com.timskiproekt.pagepicks.service.BookStatusService;
import com.timskiproekt.pagepicks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-status")
public class BookStatusController {
    @Autowired
    private BookStatusService bookStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookStatus updateBookStatus(@RequestBody BookStatus bookStatus) {
        return bookStatusService.saveBookStatus(bookStatus);
    }

    @GetMapping("/user/{username}")
    public List<BookStatus> getBookStatusesByUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return bookStatusService.findByUser(user);
    }

    @PostMapping("/update-page")
    public BookStatus updateCurrentPage(@RequestParam String username, @RequestParam Long bookId, @RequestParam int currentPage) {
        User user = userService.findByUsername(username);
        Book book = bookService.findBookById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return bookStatusService.updateCurrentPage(user, book, currentPage);
    }
}

package com.pari.library.controller;

import com.pari.library.model.Book;
import com.pari.library.model.IssuedBook;
import com.pari.library.model.User;
import com.pari.library.service.IssuedBookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issue")
public class IssuedBookController {

    private final IssuedBookService issuedBookService;

    public IssuedBookController(IssuedBookService issuedBookService) {
        this.issuedBookService = issuedBookService;
    }

    @PostMapping
    public IssuedBook issueBook(@RequestBody IssueRequest request) {
        return issuedBookService.issueBook(request.getUser(), request.getBook());
    }

    // Inner DTO class
    static class IssueRequest {
        private User user;
        private Book book;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }
    }
}

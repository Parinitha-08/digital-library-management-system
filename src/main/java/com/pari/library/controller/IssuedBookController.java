package com.pari.library.controller;

import com.pari.library.model.Book;
import com.pari.library.model.User;
import com.pari.library.service.IssuedBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IssuedBookController {

    private final IssuedBookService issuedBookService;

    public IssuedBookController(IssuedBookService issuedBookService) {
        this.issuedBookService = issuedBookService;
    }

    @PostMapping("/issue-ui")
    public String issueBook(@RequestParam Long userId,
                            @RequestParam Long bookId) {

        User user = new User();
        user.setId(userId);

        Book book = new Book();
        book.setId(bookId);

        issuedBookService.issueBook(user, book);

        return "redirect:/books-ui";
    }
}

package com.pari.library.controller;

import com.pari.library.repository.BookRepository;
import com.pari.library.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public PageController(BookRepository bookRepository,
                          UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/books-ui")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/add-book")
    public String addBookPage() {
        return "add-book";
    }

    @GetMapping("/issue-book")
    public String issueBookPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "issue-book";
    }
}

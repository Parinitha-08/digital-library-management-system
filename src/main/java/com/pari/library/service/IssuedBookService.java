package com.pari.library.service;

import com.pari.library.model.Book;
import com.pari.library.model.IssuedBook;
import com.pari.library.model.User;
import com.pari.library.repository.BookRepository;
import com.pari.library.repository.IssuedBookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssuedBookService {

    private final IssuedBookRepository issuedBookRepository;
    private final BookRepository bookRepository;

    public IssuedBookService(IssuedBookRepository issuedBookRepository,
                             BookRepository bookRepository) {
        this.issuedBookRepository = issuedBookRepository;
        this.bookRepository = bookRepository;
    }

    public IssuedBook issueBook(User user, Book book) {

        // reduce available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        IssuedBook issuedBook = new IssuedBook();
        issuedBook.setUser(user);
        issuedBook.setBook(book);
        issuedBook.setIssueDate(LocalDate.now());
        issuedBook.setFine(0);

        return issuedBookRepository.save(issuedBook);
    }
}

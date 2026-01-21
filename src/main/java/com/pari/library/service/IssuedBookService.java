package com.pari.library.service;

import com.pari.library.model.Book;
import com.pari.library.model.IssuedBook;
import com.pari.library.model.User;
import com.pari.library.repository.BookRepository;
import com.pari.library.repository.IssuedBookRepository;
import org.springframework.stereotype.Service;
import com.pari.library.repository.UserRepository;

import java.time.LocalDate;

@Service
public class IssuedBookService {

    private final IssuedBookRepository issuedBookRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public IssuedBookService(IssuedBookRepository issuedBookRepository,
                             BookRepository bookRepository,
                             UserRepository userRepository) {
        this.issuedBookRepository = issuedBookRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public IssuedBook issueBook(User userRequest, Book bookRequest) {

        // Fetch actual user from DB
        User user = userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch actual book from DB
        Book book = bookRepository.findById(bookRequest.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check availability
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        // Reduce available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        IssuedBook issuedBook = new IssuedBook();
        issuedBook.setUser(user);
        issuedBook.setBook(book);
        issuedBook.setIssueDate(LocalDate.now());
        issuedBook.setFine(0);

        return issuedBookRepository.save(issuedBook);
    }

    public IssuedBook returnBook(Long issuedBookId) {

        // find issued record
        IssuedBook issuedBook = issuedBookRepository.findById(issuedBookId)
                .orElseThrow(() -> new RuntimeException("Issued record not found"));

        // get the book
        Book book = issuedBook.getBook();

        // increase available copies
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        // set return date
        issuedBook.setReturnDate(LocalDate.now());

        // calculate fine (₹10 per day after 7 days)
        long daysIssued = java.time.temporal.ChronoUnit.DAYS.between(
                issuedBook.getIssueDate(),
                issuedBook.getReturnDate()
        );

        if (daysIssued > 7) {
            issuedBook.setFine((daysIssued - 7) * 10);
        } else {
            issuedBook.setFine(0);
        }

        return issuedBookRepository.save(issuedBook);
    }
}

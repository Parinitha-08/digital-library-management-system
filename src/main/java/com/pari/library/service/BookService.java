package com.pari.library.service;

import com.pari.library.model.Book;
import com.pari.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBookFromUi(String title, String author, int copies) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setAvailableCopies(copies);
        bookRepository.save(book);
    }

}

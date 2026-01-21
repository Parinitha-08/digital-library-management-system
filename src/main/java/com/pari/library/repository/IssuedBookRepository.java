package com.pari.library.repository;

import com.pari.library.model.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {
}

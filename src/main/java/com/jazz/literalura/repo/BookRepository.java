package com.jazz.literalura.repo;

import com.jazz.literalura.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleIgnoreCase(String title);
    List<Book> findByLanguageIgnoreCase(String language);
    long countByLanguageIgnoreCase(String language);
}

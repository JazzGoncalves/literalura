package com.jazz.literalura.repo;

import com.jazz.literalura.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndBirthYearAndDeathYear(String name, Integer birth, Integer death);
}

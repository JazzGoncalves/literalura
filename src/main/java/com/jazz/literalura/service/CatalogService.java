package com.jazz.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jazz.literalura.api.GutendexClient;
import com.jazz.literalura.domain.Author;
import com.jazz.literalura.domain.Book;
import com.jazz.literalura.repo.AuthorRepository;
import com.jazz.literalura.repo.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final GutendexClient client = new GutendexClient();

    public CatalogService(AuthorRepository authorRepo, BookRepository bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @Transactional
    public Optional<Book> searchAndSaveFirst(String title) throws Exception {
        JsonNode b = client.searchFirstByTitle(title);
        if (b == null) return Optional.empty();

        String bookTitle = b.path("title").asText();
        if (bookRepo.existsByTitleIgnoreCase(bookTitle)) {
            return bookRepo.findAll().stream()
                    .filter(x -> x.getTitle().equalsIgnoreCase(bookTitle))
                    .findFirst();
        }

        String lang = b.path("languages").isArray() && b.path("languages").size() > 0
                ? b.path("languages").get(0).asText() : "unknown";
        int downloads = b.path("download_count").asInt(0);

        JsonNode a = b.path("authors").isArray() && b.path("authors").size() > 0
                ? b.path("authors").get(0) : null;
        String name = a == null ? "Unknown" : a.path("name").asText("Unknown");
        Integer birth = a == null || a.get("birth_year") == null ? null : a.get("birth_year").asInt();
        Integer death = a == null || a.get("death_year") == null ? null : a.get("death_year").asInt();

        Author author = authorRepo.findByNameAndBirthYearAndDeathYear(name, birth, death)
                .orElseGet(() -> authorRepo.save(new Author(name, birth, death)));

        Book saved = bookRepo.save(new Book(bookTitle, lang, downloads, author));
        return Optional.of(saved);
    }

    public List<Book> listAllBooks() { return bookRepo.findAll(); }
    public List<Book> listBooksByLanguage(String lang) { return bookRepo.findByLanguageIgnoreCase(lang); }
    public long countBooksByLanguage(String lang) { return bookRepo.countByLanguageIgnoreCase(lang); }
}

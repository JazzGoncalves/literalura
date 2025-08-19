package com.jazz.literalura.domain;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    // simplificação: manter apenas o 1º idioma
    private String language;
    private Integer downloadCount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Author author;

    public Book() {}
    public Book(String title, String language, Integer downloadCount, Author author) {
        this.title = title;
        this.language = language;
        this.downloadCount = downloadCount;
        this.author = author;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public Integer getDownloadCount() { return downloadCount; }
    public Author getAuthor() { return author; }

    public void setTitle(String title) { this.title = title; }
    public void setLanguage(String language) { this.language = language; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }
    public void setAuthor(Author author) { this.author = author; }

    @Override public String toString() {
        return "Book{id=%d, title='%s', lang='%s', downloads=%s, author='%s'}"
                .formatted(id, title, language, downloadCount, author != null ? author.getName() : "?");
    }
}

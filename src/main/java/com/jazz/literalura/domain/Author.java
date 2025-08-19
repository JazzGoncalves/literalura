package com.jazz.literalura.domain;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Author() {}
    public Author(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }

    public void setName(String name) { this.name = name; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }

    @Override public String toString() {
        return "Author{id=%d, name='%s', birth=%s, death=%s}"
                .formatted(id, name, birthYear, deathYear);
    }
}

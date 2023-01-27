package com.sayonara.Library_SpringBoot.models;


import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

// идея не советует использовать Data с JPA
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(schema = "project1", name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Название книги не может быть пустым")
    @Size(max = 50, message = "Название не может быть длиннее 50-ти символов")
    private String name;

    @Column(name = "author")
    @NotBlank(message = "Имя автора не может быть пустым")
    @Size(min = 2, max = 40, message = "Имя автора должно быть от 2 до 40 символов")
    @Pattern(regexp = "(?U)[А-Я]\\w+ [А-Я]\\.", message = "Пример ввода: Сапковский А.")
    private String author;

    @Column(name = "year")
    @Range(min = 1, max = 2023, message = "Пример ввода: 1997")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public boolean hasOwner() {
        return getOwner() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && name.equals(book.name) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, year);
    }
}

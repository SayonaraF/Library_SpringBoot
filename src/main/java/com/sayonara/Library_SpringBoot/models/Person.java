package com.sayonara.Library_SpringBoot.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

// идея не советует использовать Data с JPA
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "person", schema = "project1")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "ФИО не может быть пустым")
    @Size(min = 2, max = 40, message = "ФИО должно быть от 2 до 40 символов")
    @Pattern(regexp = "(?U)[А-Я]\\w+ [А-Я]\\w+ [А-Я]\\w+", message = "Пример ввода: Иванов Иван Иванович")
    private String name;

    // не понял как правильно отобразить дату в нужном формате
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Выберите дату рождения")
    @Past(message = "Дата рождения не может быть в будущем")
    private Date birthday;

    // eager - иначе он не подгружает списки книг в класс Person
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> books;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && name.equals(person.name) && birthday.equals(person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday);
    }
}

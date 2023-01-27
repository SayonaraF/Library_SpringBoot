package com.sayonara.Library_SpringBoot.util;

import com.sayonara.Library_SpringBoot.models.Book;
import com.sayonara.Library_SpringBoot.services.BooksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class BookValidator implements Validator {
    private final BooksService booksService;

    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        Optional<Book> bookFromBD = booksService.findByName(book.getName());

        if (bookFromBD.isPresent() && booksService.findOne(book.getId()).getId() != bookFromBD.get().getId()) {
            errors.rejectValue("name", "", "Книга с таким названием уже существует");
        }
    }
}

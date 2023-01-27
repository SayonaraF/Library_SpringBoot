package com.sayonara.Library_SpringBoot.services;

import com.sayonara.Library_SpringBoot.models.Book;
import com.sayonara.Library_SpringBoot.models.Person;
import com.sayonara.Library_SpringBoot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleService peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Optional<Book> findByName(String name) {
        return booksRepository.findByName(name);
    }

    public void setPerson(int id, int personId) {
        Book book = booksRepository.findById(id).orElse(null);
        Person person = peopleRepository.findOne(personId);

        if (book != null) {
            book.setOwner(person);
        }
    }

    public void returnBook(int id) {
        booksRepository.findById(id).ifPresent(book -> book.setOwner(null));
    }

    public void save(Book book) {
        booksRepository.save(book);
    }

    // @ModelAttribute создает класс без owner, приходится назначать вручную, криво, наверно можно по другому
    public void update(Book book) {
        book.setOwner(booksRepository.findById(book.getId()).orElse(null).getOwner());

        booksRepository.save(book);
    }

    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}

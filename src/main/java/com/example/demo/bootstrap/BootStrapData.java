package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("BootStrap started");

        Publisher publisher1 = new Publisher();
        publisher1.setName("Something Publishing");
        publisher1.setCity("Stara Zagora");
        publisher1.setState("ST");
        publisherRepository.save(publisher1);

        System.out.println("Number of publishers = " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123456");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher1);
        publisher1.getBookSet().add(ddd);

        //save into h2 database
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher1);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "654321");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher1);
        publisher1.getBookSet().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher1);

        System.out.println("Number of books = " + bookRepository.count());
        System.out.println("Number fo books assigned to the publisher = " + publisher1.getBookSet().size());
    }
}

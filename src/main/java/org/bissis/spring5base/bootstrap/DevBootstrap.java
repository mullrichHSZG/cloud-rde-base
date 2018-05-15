package org.bissis.spring5base.bootstrap;

import org.bissis.spring5base.model.Author;
import org.bissis.spring5base.model.Book;
import org.bissis.spring5base.model.Publisher;
import org.bissis.spring5base.repositories.AuthorRepository;
import org.bissis.spring5base.repositories.BookRepository;
import org.bissis.spring5base.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Publisher harper = new Publisher("Harper Collins", "address I dont know");
        Book ddd = new Book("Domain Driven Design", "1234", harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(harper);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx", "another address I do not know");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);

        publisherRepository.save(worx);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}

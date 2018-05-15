package org.bissis.spring5base.repositories;

import org.bissis.spring5base.model.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {
}

package org.bissis.spring5base.repositories;

import org.bissis.spring5base.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

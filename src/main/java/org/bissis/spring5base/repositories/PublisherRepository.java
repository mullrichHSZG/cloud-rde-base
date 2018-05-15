package org.bissis.spring5base.repositories;

import org.bissis.spring5base.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}

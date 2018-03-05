package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}

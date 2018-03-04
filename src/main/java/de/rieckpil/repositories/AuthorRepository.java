package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}

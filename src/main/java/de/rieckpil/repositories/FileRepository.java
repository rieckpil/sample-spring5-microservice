package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.domain.File;

public interface FileRepository extends JpaRepository<File, Long> {

}

package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.domain.UploadFile;

public interface FileRepository extends JpaRepository<UploadFile, Long> {

}

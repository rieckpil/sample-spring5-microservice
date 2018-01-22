package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.security.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

  Privilege findByName(String name);

}

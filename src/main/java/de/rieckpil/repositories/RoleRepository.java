package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);

}

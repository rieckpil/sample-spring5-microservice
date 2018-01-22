package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

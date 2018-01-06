package de.rieckpil.repositories;

import de.rieckpil.domain.Country;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
  
  Optional<Country> findByName(String countryName);
  
}

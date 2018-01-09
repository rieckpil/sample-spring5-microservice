package de.rieckpil.repositories;

import de.rieckpil.domain.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Long> {
  
  Optional<Country> findByName(String countryName);
  
  @Query("SELECT c FROM Country c WHERE c.name=?1 AND c.validTo > CURRENT_TIMESTAMP")
  Optional<Country> findValidByName(String countryName);
  
  @Query("SELECT c FROM Country c WHERE c.validTo > CURRENT_TIMESTAMP")
  List<Country> findAll();
  
}

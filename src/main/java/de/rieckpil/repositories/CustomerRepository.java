package de.rieckpil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rieckpil.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

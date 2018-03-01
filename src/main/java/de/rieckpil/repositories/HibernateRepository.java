package de.rieckpil.repositories;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import de.rieckpil.domain.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class HibernateRepository {

  private final EntityManager entityManager;

  public void storeCountry() {

    log.info(entityManager.toString());
    Session session = entityManager.unwrap(Session.class);
    session.beginTransaction();
    session.getTransaction().commit();

    long primaryKey = 6L;
    entityManager.getTransaction().begin();
    Country countryFromHibernate = entityManager.find(Country.class, primaryKey);

    if (countryFromHibernate == null) {
      log.info(
          "Getting no entity from database while using javax.persitence.EntityManager with primary key: {}",
          primaryKey);
    } else {
      log.info("Getting entity from database while using javax.persitence.EntityManager: {}",
          countryFromHibernate.toString());
    }
    entityManager.getTransaction().commit();

  }
}

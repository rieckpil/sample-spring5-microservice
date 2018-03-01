package de.rieckpil.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import de.rieckpil.domain.City;
import de.rieckpil.domain.Country;
import de.rieckpil.domain.Gender;
import de.rieckpil.domain.Hall;
import de.rieckpil.domain.Machine;
import de.rieckpil.domain.Person;
import de.rieckpil.domain.Plant;
import de.rieckpil.repositories.CountryRepository;
import de.rieckpil.repositories.PersonRepository;
import de.rieckpil.repositories.PrivilegeRepository;
import de.rieckpil.repositories.RoleRepository;
import de.rieckpil.repositories.UserRepository;
import de.rieckpil.security.Privilege;
import de.rieckpil.security.Role;
import de.rieckpil.security.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile({"default"})
@Slf4j
@RequiredArgsConstructor
public class BootstrapH2 implements CommandLineRunner {

  private final CountryRepository countryRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PrivilegeRepository privilegeRepository;
  private final PasswordEncoder passwordEncoder;
  private final PersonRepository personRepository;

  @Override
  public void run(String... args) throws Exception {
    log.info("bootstraping H2 database ...");
    loadInitialCountries();
    loadInitialUsers();
    loadInitialPersons();
    log.info("... bootstraping H2 database finished");

  }

  @Transactional
  private void loadInitialPersons() {
    log.info("loading initial persons ...");

    for (int i = 0; i < 30; i++) {
      Person person = new Person();
      person.setFirstName("Firstname" + i);
      person.setLastName("Lastname" + i);
      person.setDob(LocalDate.now().plusDays(i));
      person.setPassportId(generateRandomString());
      person.setGender(Math.random() < 0.5 ? Gender.MALE : Gender.FEMALE);
      personRepository.save(person);
    }
  }

  @Transactional
  private void loadInitialCountries() {

    log.info("loading initial countries ...");

    List<Country> countryList = new ArrayList<Country>();

    Country us = new Country();
    us.setName("United States of America");
    us.setTimezone("Europe/Berlin");
    us.setCountryCode("USA");
    us.setDefaultLanguage("English");
    us.setLongitude(11.0);
    us.setLatitude(44.0);

    countryList.add(us);

    Country germany = new Country();
    germany.setName("Germany");
    germany.setTimezone("Europe/Berlin");
    germany.setDefaultLanguage("German");
    germany.setCountryCode("DE");
    germany.setLongitude(11.0);
    germany.setLatitude(44.0);

    City herzogenaurach = new City();
    herzogenaurach.setCountry(germany);
    herzogenaurach.setName("Herzogenaurach");
    herzogenaurach.setLatitude(113.0);
    herzogenaurach.setLongitude(56.1);

    City schweinfurt = new City();
    schweinfurt.setCountry(germany);
    schweinfurt.setName("Scheinfurt");
    schweinfurt.setLongitude(75.0);
    schweinfurt.setLatitude(33.0);

    germany.setCities(Arrays.asList(herzogenaurach, schweinfurt));

    Plant iws = new Plant();
    iws.setName("IWS");
    iws.setAbbreviation("IWS2");
    iws.setCity(herzogenaurach);

    herzogenaurach.setPlants(Arrays.asList(iws));

    Hall g1 = new Hall();
    g1.setDescription("Administration");
    g1.setName("G1");
    g1.setPlant(iws);

    iws.setHalls(Arrays.asList(g1));
    Machine m1 = new Machine();
    m1.setName("M1");
    m1.setHall(g1);

    g1.setMachines(Arrays.asList(m1));

    countryList.add(germany);

    Country france = new Country();
    france.setName("France");
    france.setCountryCode("FRA");
    france.setDefaultLanguage("french");
    france.setTimezone("Europe/Paris");
    france.setLatitude(55.0);
    france.setLongitude(66.0);

    City paris = new City();
    paris.setName("Paris");
    paris.setCountry(france);
    paris.setLatitude(55.6);
    paris.setLongitude(44.0);

    france.setCities(Arrays.asList(paris));

    Plant pp1 = new Plant();
    pp1.setName("Paris Plant I");
    pp1.setAbbreviation("PP1");
    pp1.setCity(paris);

    paris.setPlants(Arrays.asList(pp1));

    Hall g4 = new Hall();
    g4.setName("G4");
    g4.setPlant(pp1);
    g4.setDescription("IT-Administration");

    pp1.setHalls(Arrays.asList(g4));

    Machine m2 = new Machine();
    m2.setName("Machine 2");
    m2.setHall(g4);

    g4.setMachines(Arrays.asList(m2));

    countryList.add(france);

    countryRepository.saveAll(countryList);
  }

  @Transactional
  public void loadInitialUsers() {

    log.info("loading initial users ...");

    Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
    Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

    List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
    createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

    Role adminRole = roleRepository.findByName("ROLE_ADMIN");
    User user = new User();
    user.setUsername("rieckpil");
    user.setFirstName("Philip");
    user.setLastName("Riecks");
    user.setPassword(passwordEncoder.encode("test123"));
    user.setEmail("test@test.com");
    user.setRoles(Arrays.asList(adminRole));
    user.setEnabled(true);
    userRepository.save(user);
  }

  @Transactional
  private Privilege createPrivilegeIfNotFound(String name) {

    Privilege privilege = privilegeRepository.findByName(name);
    if (privilege == null) {
      privilege = new Privilege(name);
      privilegeRepository.save(privilege);
    }
    return privilege;
  }

  @Transactional
  private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

    Role role = roleRepository.findByName(name);

    if (role == null) {
      role = new Role(name);
      role.setPrivileges(privileges);
      roleRepository.save(role);
    }
    return role;
  }

  protected String generateRandomString() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 18) { // length of the random string.
      int index = (int) (rnd.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;
  }

}

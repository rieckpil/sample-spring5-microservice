package de.rieckpil.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import de.rieckpil.domain.City;
import de.rieckpil.domain.Country;
import de.rieckpil.domain.Hall;
import de.rieckpil.domain.Machine;
import de.rieckpil.domain.Plant;
import de.rieckpil.repositories.CountryRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile({"dev"})
@Slf4j
public class BootstrapPostgres implements CommandLineRunner {

	private CountryRepository countryRepository;

	public BootstrapPostgres(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("bootstraping postgres database ...");

		if (countryRepository.count() == 0) {
			log.info("initialize empty tables ...");
			countryRepository.saveAll(getCountries());
		}else {
			log.info("tables are already filled with data, no action required ...");
		}

	}

	private Iterable<Country> getCountries() {

		List<Country> countryList = new ArrayList<Country>();

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
		schweinfurt.setName("Schweinfurt");
		schweinfurt.setLongitude(75.0);
		schweinfurt.setLatitude(33.0);

		germany.setCities(Arrays.asList(herzogenaurach, schweinfurt));

		Plant iws = new Plant();
		iws.setName("IWS");
		iws.setAbbreviation("IWS 01");
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

		return countryList;

	}
}

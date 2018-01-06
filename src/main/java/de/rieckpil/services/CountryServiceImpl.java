package de.rieckpil.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.rieckpil.dtos.CountryDTO;
import de.rieckpil.mapper.CountryMapper;
import de.rieckpil.repositories.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
		this.countryRepository = countryRepository;
		this.countryMapper = countryMapper;
	}

	@Override
    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll().stream().map(countryMapper::countryToCountryDTO).collect(Collectors.toList());
    }

    @Override
    public int getAmountOfStoredCountries() {
        return getAllCountries().size();
    }
}

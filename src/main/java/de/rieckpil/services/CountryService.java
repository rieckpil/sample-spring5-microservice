package de.rieckpil.services;

import java.util.List;

import de.rieckpil.dtos.CountryDTO;

public interface CountryService {

    List<CountryDTO> getAllCountries();

    int getAmountOfCountries();
}

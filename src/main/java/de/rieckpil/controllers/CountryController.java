package de.rieckpil.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rieckpil.dtos.CountryDTO;
import de.rieckpil.services.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping({"/", ""})
    public List<CountryDTO> getCountries() {
        return countryService.getAllCountries();
    }


    @GetMapping("/amount")
    public int getAmountOfCountries() {
        return countryService.getAmountOfStoredCountries();
    }
}

package de.rieckpil.controllers;

import de.rieckpil.domain.Country;
import de.rieckpil.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping({"/", ""})
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }


    @GetMapping("/amount")
    public int getAmountOfCountries() {
        return countryService.getAmountOfCountries();
    }
}

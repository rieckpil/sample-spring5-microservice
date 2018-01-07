package de.rieckpil.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @PostMapping({"/", ""})
  @ResponseStatus(HttpStatus.CREATED)
  public void createNewCountry(@Valid @RequestBody CountryDTO country) {
    countryService.createNewCountry(country);
  }

  @GetMapping("/{countryName}")
  public CountryDTO getCountryByName(@PathVariable("countryName") final String countryName) {
    return countryService.getCountryByName(countryName);
  }

  @GetMapping("/amount")
  public int getAmountOfCountries() {
    return countryService.getAmountOfStoredCountries();
  }
}

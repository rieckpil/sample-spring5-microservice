package de.rieckpil.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    if (countryName.equals("ThrowMe")) {
      throw new RuntimeException("unavailable country requested!");
    }
    return countryService.getCountryByName(countryName);
  }

  @GetMapping("/amount")
  public int getAmountOfCountries() {

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return countryService.getAmountOfStoredCountries();
  }

  @DeleteMapping("/{countryName}")
  public void deleteCountryByName(@PathVariable("countryName") final String countryName) {
    countryService.deleteByCountryName(countryName);
  }
}

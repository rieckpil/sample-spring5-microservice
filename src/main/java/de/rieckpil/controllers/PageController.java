package de.rieckpil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.rieckpil.services.CountryService;

@Controller
public class PageController {

  private CountryService countryService;

  public PageController(CountryService countryService) {
    this.countryService = countryService;
  }

  @RequestMapping(value = "/countryList", method = RequestMethod.GET)
  public String getCountryListPage(Model model) {

    model.addAttribute("countries", countryService.getAllCountries());

    return "countryList";
  }

  @RequestMapping(value = "/country/new", method = RequestMethod.GET)
  public String createNewCountry(Model model) {
    return "countryForm";
  }
}

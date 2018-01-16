package de.rieckpil.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.rieckpil.dtos.CountryDTO;
import de.rieckpil.services.CountryService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
	public String createNewCountryForm(Model model) {

		model.addAttribute("country", new CountryDTO());

		return "countryForm";
	}

	@RequestMapping(value = "/country", method = RequestMethod.POST)
	public String createNewCountry(@Valid @ModelAttribute("country") CountryDTO countryToSave, BindingResult bindingResult) {

		log.info(String.format("Saving new country with name: '%s'", countryToSave.getName()));
		
		countryService.createNewCountry(countryToSave);

		return "redirect:/countryList";
	}
}

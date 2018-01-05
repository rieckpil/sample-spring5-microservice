package de.rieckpil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.rieckpil.services.CountryService;

@Controller
public class PageController {
	
	private CountryService countryService;

	public PageController(CountryService countryService) {
		this.countryService = countryService;
	}
	
	@RequestMapping("/countryList")
	public String getCountryListPage(Model model) {
		
		model.addAttribute("countries", countryService.getAllCountries());

		return "countryList";
	}
	
}

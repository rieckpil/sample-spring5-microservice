package de.rieckpil.controller;

import de.rieckpil.domain.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @GetMapping("/")
    public List<Country> getCountires() {
        return null;
    }
}

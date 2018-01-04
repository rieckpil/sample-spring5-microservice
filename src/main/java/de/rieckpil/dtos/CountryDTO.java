package de.rieckpil.dtos;

import java.util.List;

import lombok.Data;

@Data
public class CountryDTO {

	private String name;
	private double latitude;
	private double longitude;
	private String timezone;
	private String defaultLanguage;
	private String countryCode;
	private List<CityDTO> cities;

}

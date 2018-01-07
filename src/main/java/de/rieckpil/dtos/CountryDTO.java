package de.rieckpil.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CountryDTO {

	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private double latitude;
	
	@NotNull
	private double longitude;
	
	@NotNull
	private String timezone;
	
	@NotNull
	private String defaultLanguage;
	
	@NotNull
	private String countryCode;
	private List<CityDTO> cities = new ArrayList<CityDTO>();

}

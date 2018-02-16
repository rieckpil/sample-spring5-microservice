package de.rieckpil.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import de.rieckpil.validation.ValidTimezone;
import lombok.Data;

@Data
public class CountryDTO {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 255)
	private String name;

	@NotNull
	private double latitude;

	@NotNull
	private double longitude;

	@NotBlank
	@Size(min = 3, max = 255)
	@ValidTimezone
	private String timezone;

	@NotBlank
	@Size(min = 3, max = 255)
	private String defaultLanguage;

	@NotNull
	private String countryCode;
	private List<CityDTO> cities = new ArrayList<CityDTO>();

}

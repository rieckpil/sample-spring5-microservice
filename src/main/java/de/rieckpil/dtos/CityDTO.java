package de.rieckpil.dtos;

import java.util.List;

import lombok.Data;

@Data
public class CityDTO {

	private String name;
	private double latitude;
	private double longitude;
	private List<PlantDTO> plants;

}

package de.rieckpil.dtos;

import java.util.List;

import lombok.Data;

@Data
public class PlantDTO {

	private String name;
	private String abbreviation;
	private List<HallDTO> halls;

}

package de.rieckpil.dtos;

import java.util.List;

import lombok.Data;

@Data
public class HallDTO {
	
	  private String name;
	  private String description;
	  private List<MachineDTO> machines;

}

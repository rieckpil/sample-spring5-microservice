package de.rieckpil.dtos;

import lombok.Data;

@Data
public class CustomerDTO {

  private String firstName;
  private String lastName;
  private Long crmId;
}

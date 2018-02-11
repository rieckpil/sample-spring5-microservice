package de.rieckpil.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class UserDTO {

  @NotEmpty
  @Length(min = 6, max = 20)
  private String username;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  @Length(min = 6)
  private String password;

}

package de.rieckpil.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import de.rieckpil.domain.MessageType;
import lombok.Data;

@Data
public class MessageDTO {
  
  @NotNull
  private MessageType messageType;

  @NotEmpty
  private String message;

  @NotEmpty
  private String source;

}

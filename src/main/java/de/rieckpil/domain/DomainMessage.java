package de.rieckpil.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainMessage {

  private MessageType messageType;
  private String message;
  private String source;
  private long timestampCreated = new Date().getTime();
  
}

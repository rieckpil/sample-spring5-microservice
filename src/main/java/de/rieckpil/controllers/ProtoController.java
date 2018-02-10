package de.rieckpil.controllers;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.rieckpil.dtos.MessageDTO;
import de.rieckpil.services.ProtoService;

@RestController
@RequestMapping("/api/proto")
public class ProtoController {
  
  private final ProtoService protoService;
  
  public ProtoController(ProtoService protoService) {
    this.protoService = protoService;
  }

  @PostMapping("/messages")
  public ResponseEntity<Void> createProtoMessage(@Valid @RequestBody MessageDTO messageDto) {
    protoService.proccessMessage(messageDto);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

}

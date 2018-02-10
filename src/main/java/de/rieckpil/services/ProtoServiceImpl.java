package de.rieckpil.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Service;
import de.rieckpil.domain.DomainMessage;
import de.rieckpil.dtos.MessageDTO;
import de.rieckpil.mapper.MessageMapper;
import de.rieckpil.protobuf.MessageProto;
import de.rieckpil.protobuf.MessageProto.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProtoServiceImpl implements ProtoService {

  private final MessageMapper messageMapper;

  public ProtoServiceImpl(MessageMapper messageMapper) {
    this.messageMapper = messageMapper;
  }

  @Override
  public void proccessMessage(MessageDTO messageDto) {

    DomainMessage message = messageMapper.messageDtoToMessage(messageDto);
    log.info(String.format("Incoming message from REST interface: %s", message.toString()));

    Message messageProto = MessageProto.Message.newBuilder()
        .setMessage(message.getMessage())
        .setSource(message.getSource())
        .setTimestampCreated(message.getTimestampCreated())
      .build();
    
    try {
      boolean append = true;
      FileOutputStream output = new FileOutputStream(new File("/tmp/protbuf"), append);
      messageProto.writeTo(output);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}

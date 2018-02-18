package de.rieckpil.websockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import de.rieckpil.dtos.ChatMessageDto;

@Controller
public class ChatHandler {

  @MessageMapping("/chat")
  public void sayHello(ChatMessageDto message) {
    System.out.println(message.toString());
  }

}

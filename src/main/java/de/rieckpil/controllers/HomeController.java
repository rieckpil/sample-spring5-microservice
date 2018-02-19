package de.rieckpil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String getStartPage() {
    return "index";
  }
  
  @GetMapping("/websocket")
  public String getWebsocketPage() {
    return "websocket";
  }

  @GetMapping("/access-denied")
  public String getAccessDeniedPage() {
    return "accessDenied";
  }
}

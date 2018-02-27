package de.rieckpil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import de.rieckpil.repositories.HibernateRepository;

@Controller
public class HomeController {
  
  private final HibernateRepository hibernateRepository;
  
  public HomeController(HibernateRepository hibernateRepository) {
    this.hibernateRepository = hibernateRepository;
  }

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
  
  @GetMapping("/hibernate")
  public @ResponseBody String getHibernateInformation() {
    hibernateRepository.storeCountry();
    return "Hibernate rulez!";
  }
}

package de.rieckpil.controllers;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itextpdf.text.DocumentException;
import de.rieckpil.repositories.HibernateRepository;
import de.rieckpil.services.PdfService;

@Controller
public class HomeController {
  
  private final HibernateRepository hibernateRepository;
  private final PdfService pdfService;
  
  public HomeController(HibernateRepository hibernateRepository, PdfService pdfService) {
    this.hibernateRepository = hibernateRepository;
    this.pdfService = pdfService;
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
  
  @GetMapping("/pdf")
  public @ResponseBody String createPdf() throws IOException, DocumentException {
    pdfService.createSamplePdf();
    return "created!";
  }
}

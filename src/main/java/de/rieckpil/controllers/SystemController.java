package de.rieckpil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

  @GetMapping("/system/info")
  public String getSystemInfo() {
    return "systemInfo";
  }
}

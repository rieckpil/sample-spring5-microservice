package de.rieckpil.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import de.rieckpil.dtos.UserDTO;
import de.rieckpil.exceptions.RegistrationException;
import de.rieckpil.services.AuthService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping("/register")
  public String getRegisterPage() {
    return "registerPage";
  }

  @GetMapping("/signUp")
  public String getSignUpPage(Model model) {
    model.addAttribute("user", new UserDTO());
    return "signUp";
  }

  @PostMapping("/signUp")
  public String registerUser(@Valid @ModelAttribute("user") UserDTO userToRegister,
      BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().forEach(objectError -> {
        log.debug(objectError.toString());
      });
      return "signUp";
    }

    try {
      authService.registerUser(userToRegister);
      return "redirect:/loginPage";
    } catch (RegistrationException e) {
      log.debug(e.getMessage());
      model.addAttribute("error", e.getMessage());
      return "signUp";
    }

  }

  @GetMapping("/loginPage")
  public String getLoginPage() {
    return "loginPage";
  }
}

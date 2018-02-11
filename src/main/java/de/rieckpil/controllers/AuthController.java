package de.rieckpil.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.rieckpil.exceptions.RegistrationException;
import de.rieckpil.dtos.UserDTO;
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

  @PostMapping("/register")
  public String registerUser(@Valid @RequestBody UserDTO userToRegister,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().forEach(objectError -> {
        log.debug(objectError.toString());
      });
      return "registerPage";
    }

    try {
      authService.registerUser(userToRegister);
      return "redirect:/loginPage";
    } catch (RegistrationException e) {
      log.debug(e.getMessage());
      return "registerPage";
    }

  }

  @GetMapping("/loginPage")
  public String getLoginPage() {
    return "loginPage";
  }
}

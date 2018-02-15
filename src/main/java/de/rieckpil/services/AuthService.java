package de.rieckpil.services;

import de.rieckpil.dtos.UserDTO;
import de.rieckpil.exceptions.RegistrationException;

public interface AuthService {
  
  void registerUser(UserDTO userToRegister) throws RegistrationException;

}

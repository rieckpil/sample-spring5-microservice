package de.rieckpil.services;

import com.rieckpil.exceptions.RegistrationException;
import de.rieckpil.dtos.UserDTO;

public interface AuthService {
  
  void registerUser(UserDTO userToRegister) throws RegistrationException;

}

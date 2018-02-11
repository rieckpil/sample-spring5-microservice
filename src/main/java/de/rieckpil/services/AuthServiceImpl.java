package de.rieckpil.services;

import java.util.Arrays;
import javax.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rieckpil.exceptions.RegistrationException;
import de.rieckpil.dtos.UserDTO;
import de.rieckpil.mapper.UserMapper;
import de.rieckpil.repositories.RoleRepository;
import de.rieckpil.repositories.UserRepository;
import de.rieckpil.security.User;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper,
      RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void registerUser(UserDTO userToRegister) throws RegistrationException {

    User userToSave = userMapper.userDtoToUser(userToRegister);

    User user = userRepository.findByUsername(userToSave.getUsername());

    if (user == null) {
      user = userRepository.findByEmail(userToSave.getEmail());

      if (user == null) {
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        userToSave.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userRepository.save(userToSave);

      } else {
        throw new RegistrationException(
            String.format("E-Mail: '%s' already exists.", userToSave.getEmail()));
      }
    } else {
      throw new RegistrationException(
          String.format("Username: '%s' already exists.", userToSave.getEmail()));
    }

  }

}

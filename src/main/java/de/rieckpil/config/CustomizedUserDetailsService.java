package de.rieckpil.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import de.rieckpil.repositories.UserRepository;
import de.rieckpil.security.User;
import de.rieckpil.security.UserPrincipal;

@Service
public class CustomizedUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomizedUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new UserPrincipal(user);
  }

}

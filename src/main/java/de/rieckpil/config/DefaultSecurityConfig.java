package de.rieckpil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile({"default"})
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomizedUserDetailsService userDetailsService;

  public DefaultSecurityConfig(CustomizedUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  // @formatter:off
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
		http
		  .authorizeRequests()
		  .antMatchers("/api/**", "/h2-console/**", "/webjars/**", "/signUp")
		  .permitAll()
		 .and()
		  .authorizeRequests()
		  .antMatchers("/system/**")
		  .hasRole("ADMIN")
		 .and()
		    .authorizeRequests()
		    .anyRequest()
		    .authenticated()
		 .and()
		    .formLogin()
		      .loginPage("/loginPage")
		      .loginProcessingUrl("/login")
		      .permitAll()
		 .and()
		    .logout()
		    .permitAll()
		.and()
		    .exceptionHandling()
		    .accessDeniedPage("/access-denied")
		.and()
		  .rememberMe()
		  .userDetailsService(userDetailsService);
		
		// configurations for enabling h2-console behind Spring Security
		http.csrf().disable();
		
		http.headers().frameOptions().disable();
  }
  // @formatter:on

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {

    // UserBuilder users = User.withDefaultPasswordEncoder();
    //
    // auth.inMemoryAuthentication()
    // .withUser(users.username("user").password("password").roles("USER"))
    // .withUser(users.username("max").password("password").roles("USER"))
    // .withUser(users.username("mary").password("password").roles("ADMIN"));

    auth.authenticationProvider(authenticationProvider());

  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }
}

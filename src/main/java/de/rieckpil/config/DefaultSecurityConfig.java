package de.rieckpil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Profile({"default"})
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {

  // @formatter:off
  protected void configure(HttpSecurity http) throws Exception {
		http
		  .authorizeRequests()
		  .antMatchers("/api/**", "/h2-console/**")
		  .permitAll()
		  .and()
		    .authorizeRequests()
		    .anyRequest()
		    .authenticated()
		  .and()
		    .httpBasic();
		
		// configurations for enabling h2-console behind Spring Security
		http.csrf().disable();
		
		http.headers().frameOptions().disable();
  }
  // @formatter:on

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    
    auth.inMemoryAuthentication()
      .withUser("user").password("password").roles("USER").and()
      .withUser("max").password("password").roles("ADMIN").and()
      .withUser("tom").password("password").roles("MANAGER");
  }
}

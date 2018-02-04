package de.rieckpil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
@Profile({"default"})
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {

  // @formatter:off
  @Override
  protected void configure(HttpSecurity http) throws Exception {
		http
		  .authorizeRequests()
		  .antMatchers("/api/**", "/h2-console/**", "/webjars/**")
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
		    .accessDeniedPage("/access-denied");
		
		// configurations for enabling h2-console behind Spring Security
		http.csrf().disable();
		
		http.headers().frameOptions().disable();
  }
  // @formatter:on

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {

    UserBuilder users = User.withDefaultPasswordEncoder();

    auth.inMemoryAuthentication()
        .withUser(users.username("user").password("password").roles("USER"))
        .withUser(users.username("max").password("password").roles("USER"))
        .withUser(users.username("mary").password("password").roles("ADMIN"));
  }
}

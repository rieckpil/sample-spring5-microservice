package de.rieckpil.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

  private JavaMailSender emailSender;

  public MailServiceImpl(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  @Override
  public void sendRegistrationMail(String email) {
    
    SimpleMailMessage message = new SimpleMailMessage(); 
    message.setTo(email); 
    message.setSubject("Welcome to the Sample Spring 5 Microservice"); 
    message.setText("You account was registered correctly. You can now login");
    emailSender.send(message);

  }

}

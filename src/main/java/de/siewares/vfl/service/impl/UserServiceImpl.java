/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Mail;
import de.siewares.vfl.entity.User;
import de.siewares.vfl.repository.UserRepository;
import de.siewares.vfl.service.MailService;
import de.siewares.vfl.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sievers
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private MailService mailService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void changePassword(String newPassword) {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    List<User> users = userRepository.findByUsername(userName);
    if (users.size() == 1) {
      User user = users.get(0);
      user.setPassword(passwordEncoder.encode(newPassword));
      userRepository.save(user);
    } else {
      //@todo error response
    }
  }

  @Override
  public User get() {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
     List<User> users = userRepository.findByUsername(userName);
    if (users.size() == 1) {
      User user = users.get(0);
      return user;
    } else {
      return null;
      //@todo error response
    }
  }  
  
  
  @Override
   public void sendMail(Mail mail) {
    mailService.sendMail(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getText());
  }

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
  }

  @Override
  public User get(int id) {
    User result = userRepository.getOne(id);
    return result;
  }

}

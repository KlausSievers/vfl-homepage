/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Mail;
import de.siewares.vfl.entity.User;
import de.siewares.vfl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sievers
 */
@RestController
@RequestMapping("/mail")
public class MailResource {

  @Autowired
  private UserService service;

  @RequestMapping(value = "{id}", method = RequestMethod.POST)
  void sendMail(@PathVariable int id, @RequestBody Mail mail) {
    User person = service.get(id);
    mail.setTo(person.getMail());
    service.sendMail(mail);
  }
}

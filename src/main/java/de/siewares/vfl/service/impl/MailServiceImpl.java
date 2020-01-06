/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import de.siewares.vfl.service.MailService;

/**
 *
 * @author Klaus
 */
@Service
public class MailServiceImpl implements MailService {

  @Autowired
  private MailSender mailSender;

  @Override
  public void sendMail(String from, String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom("kontakt@vfl-grafenwald-jugend.de");
    message.setTo(to);
    message.setSubject(subject);
    message.setReplyTo(from);
    message.setText(text);

    mailSender.send(message);

  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

/**
 *
 * @author Klaus
 */
public interface MailService {

  void sendMail(String from, String to, String subject, String text);
  
}

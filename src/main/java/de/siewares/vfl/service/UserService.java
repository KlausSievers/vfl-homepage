/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

import de.siewares.vfl.entity.Mail;
import de.siewares.vfl.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sievers
 */
public interface UserService {
  public User get(int id);
  public User get();
  public void changePassword(String newPassword);
  public void sendMail(Mail mail);
  public void logout(HttpServletRequest request, HttpServletResponse response);
}

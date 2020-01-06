/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

import de.siewares.vfl.entity.Mail;
import de.siewares.vfl.entity.Management;
import java.util.List;

/**
 *
 * @author Klaus
 */
public interface ManagementService {

  Management get(int id);

  List<Management> getAll();
  List<Management> getYouth();
  List<Management> getSenior();  
}

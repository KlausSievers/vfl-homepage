/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.constant.ManagementType;
import de.siewares.vfl.entity.Mail;
import de.siewares.vfl.entity.Management;
import de.siewares.vfl.repository.ManagementRepository;
import de.siewares.vfl.service.MailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.siewares.vfl.service.ManagementService;

/**
 *
 * @author Klaus
 */
@Service
public class ManagementServiceImpl implements ManagementService {

  @Autowired
  private ManagementRepository repository;

  @Override
  public List<Management> getAll() {
    List<Management> result = this.repository.findAll();
    return result;
  }

  @Override
  public List<Management> getSenior() {
    List<Management> result = this.repository.findByType(ManagementType.SENIOR.getValue());
    return result;
  }

  @Override
  public List<Management> getYouth() {
    List<Management> result = this.repository.findByType(ManagementType.YOUTH.getValue());
    return result;
  }

  @Override
  public Management get(int id) {
    Management result = repository.getOne(id);
    return result;
  }

}

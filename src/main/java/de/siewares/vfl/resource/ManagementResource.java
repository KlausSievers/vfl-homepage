/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Mail;
import de.siewares.vfl.entity.Management;
import de.siewares.vfl.service.ManagementService;
import de.siewares.vfl.service.impl.ManagementServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Klaus
 */
@RestController
@RequestMapping("/management")
public class ManagementResource {

  @Autowired
  private ManagementService service;

  @RequestMapping(value = "youth", method = RequestMethod.GET)
  List<Management> queryYouth() {
    return this.service.getYouth();
  }

  @RequestMapping(value = "senior", method = RequestMethod.GET)
  List<Management> querySenior() {
    return this.service.getSenior();
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Club;
import de.siewares.vfl.repository.ClubRepository;
import de.siewares.vfl.service.ClubService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sievers
 */
@Service
public class ClubServiceImpl implements ClubService{
  @Autowired
  private ClubRepository clubRepository;
  
  @Override
  public List<Club> getAll(){
    return clubRepository.findAll();
  }
}

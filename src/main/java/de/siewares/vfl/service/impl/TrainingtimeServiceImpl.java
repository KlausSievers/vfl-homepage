/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Team;
import de.siewares.vfl.entity.Trainingtime;
import de.siewares.vfl.repository.TrainingtimeRepository;
import de.siewares.vfl.service.TrainingtimeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sievers
 */
@Service
public class TrainingtimeServiceImpl implements TrainingtimeService {

  @Autowired
  private TrainingtimeRepository trainingtimeRepository;

  @Override
  public List<Trainingtime> getByTeam(Team team) {
    return trainingtimeRepository.findByTeam(team);
  }

}

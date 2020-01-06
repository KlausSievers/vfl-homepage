/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

import de.siewares.vfl.entity.Team;
import de.siewares.vfl.entity.Trainingtime;
import java.util.List;

/**
 *
 * @author Sievers
 */
public interface TrainingtimeService {
  
  List<Trainingtime> getByTeam(Team team);
}

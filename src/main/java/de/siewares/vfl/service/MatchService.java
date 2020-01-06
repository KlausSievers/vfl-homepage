/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

import de.siewares.vfl.entity.Match;
import de.siewares.vfl.entity.Team;
import java.util.List;

/**
 *
 * @author Klaus
 */
public interface MatchService {

  List<Match> getAll();  
  Match save(Match match);
  void delete(int id);
  Match getNextMatch(Team team);
  Match getPrevMatch(Team team);
  List<Match> getNextMatches();
  List<Match> getPrevMatches();
  
}

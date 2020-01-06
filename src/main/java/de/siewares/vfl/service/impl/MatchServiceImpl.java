/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Match;
import de.siewares.vfl.entity.Team;
import de.siewares.vfl.repository.MatchRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.siewares.vfl.service.MatchService;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author Klaus
 */
@Service
public class MatchServiceImpl implements MatchService {

  @Autowired
  private MatchRepository matchRepository;

  @Override
  public List<Match> getAll() {
    List<Match> result = matchRepository.findAll();
    return result;
  }

  @Override
  public Match save(Match match) {
    return matchRepository.save(match);
  }

  @Override
  public void delete(int id) {
    matchRepository.delete(id);
  }

  @Override
  public Match getNextMatch(Team team) {
    List<Match> nextMatches = matchRepository.findByTeamAndKickoffAfterOrderByKickoffAsc(team, new Date());
    if (!nextMatches.isEmpty()) {
      return nextMatches.get(0);
    }
    return null;
  }

  @Override
  public Match getPrevMatch(Team team) {
    List<Match> prevMatches = matchRepository.findByTeamAndKickoffBeforeOrderByKickoffDesc(team, new Date());
    if (!prevMatches.isEmpty()) {
      return prevMatches.get(0);
    }
    return null;
  }

  @Override
  public List<Match> getNextMatches() {
    LocalDate nextWeek = LocalDate.now().plusWeeks(1);
    Date dateNextWeek = Date.from(nextWeek.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    return matchRepository.findByKickoffBetween(new Date(), dateNextWeek);
  }

  @Override
  public List<Match> getPrevMatches() {
    LocalDate prevWeek = LocalDate.now().minusWeeks(1);
    Date datePrevWeek = Date.from(prevWeek.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    return matchRepository.findByKickoffBetween(datePrevWeek, new Date());
  }
}

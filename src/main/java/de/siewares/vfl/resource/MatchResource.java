/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Match;
import de.siewares.vfl.entity.Team;
import de.siewares.vfl.service.MatchService;
import de.siewares.vfl.service.TeamService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Klaus
 */
@RestController
@RequestMapping("/match")
public class MatchResource {

  @Autowired
  private TeamService teamService;
  @Autowired
  private MatchService matchService;

  @RequestMapping(method = RequestMethod.GET)
  List<Match> query() {
    return this.matchService.getAll();
  }

  @PostMapping(value = "/{id}")
  public Match update(@RequestBody Match match) {
    return matchService.save(match);
  }

  @PostMapping()
  public Match create(@RequestBody Match match) {
    return matchService.save(match);
  }

  @DeleteMapping(value = "{id}")
  public void delete(@PathVariable int id) {
    matchService.delete(id);
  }

  @RequestMapping(value = "team/{shortName}", method = RequestMethod.GET)
  List<Match> getMatchesToTeam(@PathVariable("shortName") String shortName) {
    Team team = this.teamService.getByShortName(shortName);
    return team.getMatchList();
  }

  @RequestMapping(value = "team/{shortName}/next", method = RequestMethod.GET)
  Match getNextMatchToTeam(@PathVariable("shortName") String shortName) {
    Team team = this.teamService.getByShortName(shortName);
    return matchService.getNextMatch(team);
  }

  @RequestMapping(value = "team/{shortName}/prev", method = RequestMethod.GET)
  Match getPrevMatchToTeam(@PathVariable("shortName") String shortName) {
    Team team = this.teamService.getByShortName(shortName);
    return matchService.getPrevMatch(team);
  }

  @RequestMapping(value = "/next", method = RequestMethod.GET)
  List<Match> getNextMatches() {
    return matchService.getNextMatches();
  }

  @RequestMapping(value = "/prev", method = RequestMethod.GET)
  List<Match> getPrevMatches() {
    return matchService.getPrevMatches();
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Team;
import de.siewares.vfl.entity.Trainingtime;
import de.siewares.vfl.service.TeamService;
import de.siewares.vfl.service.TrainingtimeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sievers
 */
@RestController
@RequestMapping("/trainingtime")
public class TrainingtimeResource {

  @Autowired
  private TrainingtimeService trainingtimeService;
  
  @Autowired
  private TeamService teamService;

  @RequestMapping(value = "{shortName}", method = RequestMethod.GET)
  List<Trainingtime> get(@PathVariable("shortName") String shortName) {
    Team team = teamService.getByShortName(shortName);
    return this.trainingtimeService.getByTeam(team);
  }

}

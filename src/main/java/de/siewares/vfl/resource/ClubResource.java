/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Club;
import de.siewares.vfl.service.ClubService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sievers
 */
@RestController
@RequestMapping("/club")
public class ClubResource {
  @Autowired
  private ClubService clubService;

  @RequestMapping(method = RequestMethod.GET)
  List<Club> query() {
    return clubService.getAll();
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.repository;

import de.siewares.vfl.entity.Team;
import de.siewares.vfl.entity.Trainingtime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sievers
 */
public interface TrainingtimeRepository extends JpaRepository<Trainingtime, Integer> {
  List<Trainingtime> findByTeam(Team team);
}

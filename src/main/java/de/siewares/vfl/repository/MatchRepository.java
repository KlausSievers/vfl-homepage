/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.repository;

import de.siewares.vfl.entity.Match;
import de.siewares.vfl.entity.Team;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Klaus
 */
public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findByTeamAndKickoffBeforeOrderByKickoffDesc(Team team, Date kickoff);
    List<Match> findByTeamAndKickoffAfterOrderByKickoffAsc(Team team, Date kickoff);   
    List<Match> findByKickoffBeforeOrderByKickoffDesc(Date kickoff);
    List<Match> findByKickoffAfterOrderByKickoffAsc(Date kickoff);    
    List<Match> findByKickoffBetween (Date start, Date end);
}

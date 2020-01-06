/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.repository;

import de.siewares.vfl.entity.Event;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sievers
 */
public interface EventRepository   extends JpaRepository<Event, Integer>{
  List<Event<?>> findByStartBetween (LocalDateTime start, LocalDateTime end);
}

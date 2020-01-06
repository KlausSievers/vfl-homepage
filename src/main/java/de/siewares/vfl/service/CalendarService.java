/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

import de.siewares.vfl.entity.Event;
import de.siewares.vfl.entity.Match;
import de.siewares.vfl.entity.Trainingtime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Sievers
 */
public interface CalendarService {

  public List<Event<?>> getAll(LocalDateTime start, LocalDateTime end);
  public List<Event<Trainingtime>> getTrainingTimeCalendar(LocalDate start, LocalDate end);
  public List<Event<Match>> getMatchCalendar(LocalDate start, LocalDate end);  
  public List<Event<?>> getEventCalendar(LocalDateTime start, LocalDateTime end);  
  public Event<?> save(Event<?> event);
}

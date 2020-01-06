/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

import de.siewares.vfl.entity.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Sievers
 */
public interface ResourceCalendarService {
  public List<Event<?>> getAll(LocalDate start, LocalDate end);
}

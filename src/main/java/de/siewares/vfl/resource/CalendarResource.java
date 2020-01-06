/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Event;
import de.siewares.vfl.service.CalendarService;
import de.siewares.vfl.service.ResourceCalendarService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sievers
 */
@RestController
@RequestMapping("/calendar")
public class CalendarResource {

  @Autowired
  private CalendarService calendarService;

  @Autowired
  private ResourceCalendarService resourceCalendarService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Event<?>> getAll(@RequestParam("start") String start, @RequestParam("end") String end) {
    return calendarService.getAll(OffsetDateTime.parse(start).toLocalDateTime(), OffsetDateTime.parse(end).toLocalDateTime());
  }

  @RequestMapping(value = "resource", method = RequestMethod.GET)
  public List<Event<?>> getAllResource(@RequestParam("start") String start, @RequestParam("end") String end) {
    return resourceCalendarService.getAll(OffsetDateTime.parse(start).toLocalDate(), OffsetDateTime.parse(end).toLocalDate());
  }

  @PostMapping
  public Event<?> saveEvent(@RequestBody Event<?> event) {
    return calendarService.save(event);
  }

}

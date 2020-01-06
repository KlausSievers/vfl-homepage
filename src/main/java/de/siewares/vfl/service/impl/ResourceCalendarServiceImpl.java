/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Event;
import de.siewares.vfl.entity.Match;
import de.siewares.vfl.entity.Trainingtime;
import de.siewares.vfl.service.CalendarService;
import de.siewares.vfl.service.ResourceCalendarService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sievers
 */
@Service
public class ResourceCalendarServiceImpl implements ResourceCalendarService {

  @Autowired
  private CalendarService calendarService;

  @Override
  public List<Event<?>> getAll(LocalDate start, LocalDate end) {
    List<Event<Trainingtime>> trainingTimeCalendar = calendarService.getTrainingTimeCalendar(start, end);
    List<Event<Match>> matchCalendar = calendarService.getMatchCalendar(start, end);
    List<Event<?>> customEventCalendar = calendarService.getEventCalendar(start.atStartOfDay(), end.atTime(23, 59));

    List<Event<Trainingtime>> sportFieldsFromTraining = getSportFieldsByTrainingtime(trainingTimeCalendar);
    List<Event<Match>> sportFieldsFromMatch = getSportFieldsByMatches(matchCalendar);

    List<Event<Trainingtime>> lockerRoomsFromTraining = getLockerRoomsByTrainingtime(trainingTimeCalendar);
    List<Event<Match>> lockerRoomsFromMatch = getLockerRoomsByMatch(matchCalendar);

    List<Event<?>> customEvents = getCustomEvents(customEventCalendar);

    List<Event<?>> result = new ArrayList<>();
    result.addAll(sportFieldsFromTraining);
    result.addAll(sportFieldsFromMatch);
    result.addAll(lockerRoomsFromTraining);
    result.addAll(lockerRoomsFromMatch);
    result.addAll(customEvents);

    return result;
  }

  private List<Event<Trainingtime>> getSportFieldsByTrainingtime(List<Event<Trainingtime>> trainingtimeEvents) {
    List<Event<Trainingtime>> result = new LinkedList<>();
    trainingtimeEvents.stream().forEach((event) -> {
      Event e = new Event(event);
      e.setResourceId("P" + event.getParentObj().getSportsField());
      result.add(e);
    });

    return result;
  }

  private List<Event<Match>> getSportFieldsByMatches(List<Event<Match>> matchEvents) {
    List<Event<Match>> result = new LinkedList<>();
    matchEvents.stream().forEach((event) -> {
      Event e = new Event(event);
      e.setResourceId("P" + event.getParentObj().getSportsField());
      result.add(e);
    });

    return result;
  }

  private List<Event<Trainingtime>> getLockerRoomsByTrainingtime(List<Event<Trainingtime>> trainingtimeEvents) {
    List<Event<Trainingtime>> result = new LinkedList<>();
    trainingtimeEvents.stream().forEach((event) -> {
      Event e = new Event(event);
      e.setStart(e.getStart().minusMinutes(30));
      e.setEnd(e.getEnd().plusMinutes(30));
      e.setResourceId("K" + event.getParentObj().getLockerRoom());
      result.add(e);
    });

    return result;
  }

  private List<Event<Match>> getLockerRoomsByMatch(List<Event<Match>> matchEvents) {
    List<Event<Match>> result = new LinkedList<>();
    matchEvents.stream().forEach((event) -> {
      if (event.getParentObj().isHome()) {
        Event e1 = new Event(event);
        e1.setStart(e1.getStart().minusMinutes(30));
        e1.setEnd(e1.getEnd().plusMinutes(30));
        e1.setResourceId("K" + event.getParentObj().getLockerRoom());
        result.add(e1);

        Event e2 = new Event(event);
        e2.setStart(e2.getStart().minusMinutes(30));
        e2.setEnd(e2.getEnd().plusMinutes(30));
        e2.setResourceId("K" + event.getParentObj().getLockerRoomOpponent());
        result.add(e2);
      }
    });

    return result;
  }

  private List<Event<?>> getCustomEvents(List<Event<?>> customEvents) {
    List<Event<?>> result = new LinkedList<>();
    customEvents.stream().forEach((event) -> {
      if (event.getResourceId() != null) {
        String[] resources = event.getResourceId().split(",");
        for (String resource : resources) {
          Event e = new Event(event);
          e.setResourceId(resource);
          result.add(e);
        }
      }
    });

    return result;
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Event;
import de.siewares.vfl.entity.Match;
import de.siewares.vfl.entity.Team;
import de.siewares.vfl.entity.Trainingtime;
import de.siewares.vfl.repository.EventRepository;
import de.siewares.vfl.repository.MatchRepository;
import de.siewares.vfl.repository.TeamRepository;
import de.siewares.vfl.service.CalendarService;
import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sievers
 */
@Service
public class CalendarServiceImp implements CalendarService {

  @Autowired
  private TeamRepository teamRepository;
  @Autowired
  private MatchRepository matchRepository;
  @Autowired
  private EventRepository eventRepository;

  @Override
  public List<Event<?>> getAll(LocalDateTime start, LocalDateTime end) {
    List<Event<Trainingtime>> trainingTimeCalendar = getTrainingTimeCalendar(start.toLocalDate(), end.toLocalDate());
    List<Event<Match>> matchCalendar = getMatchCalendar(start.toLocalDate(), end.toLocalDate());
    List<Event<?>> eventCalendar = getEventCalendar(start, end);

    List<Event<?>> result = new ArrayList<>();
    result.addAll(trainingTimeCalendar);
    result.addAll(matchCalendar);
    result.addAll(eventCalendar);
    return result;
  }

  @Override
  public List<Event<Trainingtime>> getTrainingTimeCalendar(LocalDate start, LocalDate end) {
    List<Team> teams = teamRepository.findAll();
    List<Event<Trainingtime>> trainingTimeEvents = new LinkedList<>();
    for (Team team : teams) {
      List<Trainingtime> trainingtimes = team.getTrainingtimeList();
      for (Trainingtime trainingtime : trainingtimes) {
        DayOfWeek day = DayOfWeek.of(trainingtime.getDayOfWeek());
        LocalDate nextTrainingDay = start.with(TemporalAdjusters.nextOrSame(day));

        Date time = trainingtime.getStartTime();
        LocalTime nextTrainingTime = LocalTime.of(time.getHours(), time.getMinutes());
        while (!nextTrainingDay.isAfter(end)) {
          Event<Trainingtime> event = new Event(team.getShortName() + "-Training", LocalDateTime.of(nextTrainingDay, nextTrainingTime));
          event.setEnd(LocalDateTime.of(nextTrainingDay, LocalTime.of(trainingtime.getEndTime().getHours(), trainingtime.getEndTime().getMinutes())));
          event.setBackgroundColor("#4285F4");
          event.setBorderColor("#4285F4");
          event.setParentObj(trainingtime);

          trainingTimeEvents.add(event);

          nextTrainingDay = nextTrainingDay.with(TemporalAdjusters.next(day));
        }

      }
    }

    return trainingTimeEvents;
  }

  @Override
  public List<Event<Match>> getMatchCalendar(LocalDate start, LocalDate end) {
    List<Match> matches = matchRepository.findByKickoffBetween(convertToDateViaInstant(start), convertToDateViaInstant(end));
    List<Event<Match>> matchEvents = new LinkedList<>();
    for (Match match : matches) {
      Date startMatch = match.getKickoff();
      LocalDateTime endMatch = getMatchEnd(startMatch);
      String title = getMatchTitle(match);

      LocalDateTime startMatchLdt = startMatch.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

      Event event = new Event(title, startMatchLdt);
      event.setEnd(endMatch);
      event.setBackgroundColor("#7CB342");
      event.setBorderColor("#7CB342");
      event.setParentObj(match);

      matchEvents.add(event);
    }

    return matchEvents;
  }

  @Override
  public List<Event<?>> getEventCalendar(LocalDateTime start, LocalDateTime end) {
    List<Event> events = eventRepository.findAll();
    List<Event<?>> result = new LinkedList<>();
    events.stream().forEach((e) -> {
      e.setBackgroundColor("#B34442");
      e.setBorderColor("#B34442");
      result.add(e);
    });

    return result;
  }

  private LocalDateTime getMatchEnd(Date kickoff) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(kickoff);
    cal.add(Calendar.MINUTE, 90);
    Date endDate = cal.getTime();
    return endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  private String getMatchTitle(Match match) {
    if (match.isHome()) {
      return match.getTeam().getShortName() + "-" + match.getOpponent().getName();
    } else {
      return match.getOpponent().getName() + "-" + match.getTeam().getShortName();
    }
  }

  private Date convertToDateViaInstant(LocalDate dateToConvert) {
    return java.util.Date.from(dateToConvert.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());
  }

  @Override
  public Event<?> save(Event<?> event) {
    return eventRepository.save(event);
  }

}

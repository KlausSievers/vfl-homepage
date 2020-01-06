/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Klaus
 */
@Entity
@Table(name = "[match]")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Match.findAll", query = "SELECT m FROM Match m")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Match implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "kickoff")
  @Temporal(TemporalType.TIMESTAMP)
  private Date kickoff;
  @Basic(optional = false)
  @NotNull
  @Column(name = "is_home")
  private boolean home;
  @Column(name = "goals_home")
  private Integer goalsHome;
  @Column(name = "goals_away")
  private Integer goalsAway;
  @Column(name = "is_canceled")
  private Boolean canceled;
  @Column(name = "is_aborted")
  private Boolean aborted;
  @Column(name = "sports_field")
  private Integer sportsField;
  @Column(name = "locker_room")
  private Integer lockerRoom;
  @Column(name = "locker_room_opponent")
  private Integer lockerRoomOpponent;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
  private List<Goal> goalList;
  @JoinColumn(name = "opponent", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Club opponent;
  @JoinColumn(name = "team", referencedColumnName = "id")
  @ManyToOne(optional = false)
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "teamId",
          resolver = EntityIdResolver.class, scope = Team.class)
  //@JsonIdentityReference(alwaysAsId = true)
  private Team team;

  public Match() {
  }

  public Match(Integer id) {
    this.id = id;
  }

  public Match(Integer id, Date kickoff, boolean home) {
    this.id = id;
    this.kickoff = kickoff;
    this.home = home;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getKickoff() {
    return kickoff;
  }

  public void setKickoff(Date kickoff) {
    this.kickoff = kickoff;
  }

  public boolean isHome() {
    return home;
  }

  public void setHome(boolean home) {
    this.home = home;
  }

  public Integer getSportsField() {
    return sportsField;
  }

  public void setSportsField(Integer sportsField) {
    this.sportsField = sportsField;
  }

  public Integer getLockerRoom() {
    return lockerRoom;
  }

  public void setLockerRoom(Integer lockerRoom) {
    this.lockerRoom = lockerRoom;
  }

  public Integer getLockerRoomOpponent() {
    return lockerRoomOpponent;
  }

  public void setLockerRoomOpponent(Integer lockerRoomOpponent) {
    this.lockerRoomOpponent = lockerRoomOpponent;
  } 
  
  public Integer getGoalsHome() {
    return goalsHome;
  }

  public void setGoalsHome(Integer goalsHome) {
    this.goalsHome = goalsHome;
  }

  public Integer getGoalsAway() {
    return goalsAway;
  }

  public void setGoalsAway(Integer goalsAway) {
    this.goalsAway = goalsAway;
  }

  public Boolean isCanceled() {
    return canceled;
  }

  public void setCanceled(Boolean canceled) {
    this.canceled = canceled;
  }

  public Boolean isAborted() {
    return aborted;
  }

  public void setAborted(Boolean aborted) {
    this.aborted = aborted;
  }

  @XmlTransient
  public List<Goal> getGoalList() {
    return goalList;
  }

  public void setGoalList(List<Goal> goalList) {
    this.goalList = goalList;
  }

  public Club getOpponent() {
    return opponent;
  }

  public void setOpponent(Club opponent) {
    this.opponent = opponent;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + this.id;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Match other = (Match) obj;
    if (this.id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.siewares.vfl.entity.Match[ id=" + id + " ]";
  }

}

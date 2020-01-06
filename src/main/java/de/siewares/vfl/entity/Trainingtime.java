/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Klaus
 */
@Entity
@Table(name = "trainingtime")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Trainingtime.findAll", query = "SELECT t FROM Trainingtime t")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "trainingtimeId")
public class Trainingtime implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  @JsonProperty("trainingtimeId")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "day_of_week")
  private int dayOfWeek;
  @Basic(optional = false)
  @NotNull
  @Column(name = "start_time")
  @Temporal(TemporalType.TIME)
  @JsonFormat(shape = JsonFormat.Shape.NUMBER)
  private Date startTime;
  @Basic(optional = false)
  @NotNull
  @Column(name = "end_time")
  @Temporal(TemporalType.TIME)
  private Date endTime;
  @Basic(optional = false)
  @NotNull
  @Column(name = "sports_field")
  private int sportsField;
  @Basic(optional = false)
  @NotNull
  @Column(name = "locker_room")
  private int lockerRoom;
  @JoinColumn(name = "team", referencedColumnName = "id")
  @ManyToOne(optional = false)
  @JsonBackReference(value = "team_training")
  private Team team;

  public Trainingtime() {
  }

  public Trainingtime(Integer id) {
    this.id = id;
  }

  public Trainingtime(Integer id, int dayOfWeek, Date startTime, Date endTime) {
    this.id = id;
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(int dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public int getSportsField() {
    return sportsField;
  }

  public void setSportsField(int sportsField) {
    this.sportsField = sportsField;
  }

  public int getLockerRoom() {
    return lockerRoom;
  }

  public void setLockerRoom(int lockerRoom) {
    this.lockerRoom = lockerRoom;
  }
 
  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Trainingtime)) {
      return false;
    }
    Trainingtime other = (Trainingtime) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.siewares.vfl.entity.Trainingtime[ id=" + id + " ]";
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.siewares.vfl.constant.Rendering;
import java.awt.Color;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @see https://fullcalendar.io/docs/event-object
 * @author Sievers
 */
@Entity
@Table(name = "custom_event")
@XmlRootElement
public class Event<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  private Integer id;
  @Basic(optional = false)
  @NotNull
  private String title;
  private Boolean allDay = false;
  private LocalDateTime start;
  private LocalDateTime end;
//  private URL url;
  @Transient
  private List<String> className;
  private Boolean editable;
  private Boolean startEditable;
  private Boolean durationEditable;
  private Boolean resourceEditable;
  @Transient
  private Rendering rendering;
  private Boolean overlap;
  private String backgroundColor;
  private String borderColor;
  private String textColor;
  private String resourceId;
  @Transient
  private T parentObj;

  public Event(String title, LocalDateTime start) {
    this.title = title;
    this.start = start;
  }

  public Event() {

  }

  public Event(Event<T> e) {
    this.id = e.id;
    this.title = e.title;
    this.allDay = e.allDay;
    this.start = e.start;
    this.end = e.end;
//    this.url = e.url;
    this.className = e.className;
    this.editable = e.editable;
    this.startEditable = e.startEditable;
    this.durationEditable = e.durationEditable;
    this.resourceEditable = e.resourceEditable;
    this.rendering = e.rendering;
    this.overlap = e.overlap;
    this.backgroundColor = e.backgroundColor;
    this.borderColor = e.borderColor;
    this.textColor = e.textColor;
    this.resourceId = e.resourceId;
    this.parentObj = e.parentObj;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getAllDay() {
    return allDay;
  }

  public void setAllDay(Boolean allDay) {
    this.allDay = allDay;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
  }

//  public URL getUrl() {
//    return url;
//  }
//
//  public void setUrl(URL url) {
//    this.url = url;
//  }
  public List<String> getClassName() {
    return className;
  }

  public void setClassName(List<String> className) {
    this.className = className;
  }

  public Boolean getEditable() {
    return editable;
  }

  public void setEditable(Boolean editable) {
    this.editable = editable;
  }

  public Boolean getStartEditable() {
    return startEditable;
  }

  public void setStartEditable(Boolean startEditable) {
    this.startEditable = startEditable;
  }

  public Boolean getDurationEditable() {
    return durationEditable;
  }

  public void setDurationEditable(Boolean durationEditable) {
    this.durationEditable = durationEditable;
  }

  public Boolean getResourceEditable() {
    return resourceEditable;
  }

  public void setResourceEditable(Boolean resourceEditable) {
    this.resourceEditable = resourceEditable;
  }

  public Rendering getRendering() {
    return rendering;
  }

  public void setRendering(Rendering rendering) {
    this.rendering = rendering;
  }

  public Boolean getOverlap() {
    return overlap;
  }

  public void setOverlap(Boolean overlap) {
    this.overlap = overlap;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public String getBorderColor() {
    return borderColor;
  }

  public void setBorderColor(String borderColor) {
    this.borderColor = borderColor;
  }

  public String getTextColor() {
    return textColor;
  }

  public void setTextColor(String textColor) {
    this.textColor = textColor;
  }

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public T getParentObj() {
    return parentObj;
  }

  public void setParentObj(T parentObj) {
    this.parentObj = parentObj;
  }

}

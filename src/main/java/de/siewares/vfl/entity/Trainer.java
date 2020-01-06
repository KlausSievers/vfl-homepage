/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Klaus
 */
@Entity
@Table(name = "trainer")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Trainer.findAll", query = "SELECT t FROM Trainer t")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trainer implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  
  @JoinTable(name = "trainer_team",
          joinColumns = {
            @JoinColumn(name = "trainer", nullable = false, updatable = false)},
          inverseJoinColumns = {
            @JoinColumn(name = "team", nullable = false, updatable = false)}
  )
  @ManyToMany(cascade = CascadeType.ALL)
  @JsonBackReference(value="team_trainer")
  private List<Team> teamList;
  @JoinColumn(name = "user", referencedColumnName = "id")
  @ManyToOne
  @JsonUnwrapped
  //@JsonManagedReference(value="trainer_user")
  private User user;

  public Trainer() {
  }

  public Trainer(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @XmlTransient
  public List<Team> getTeamList() {
    return teamList;
  }

  public void setTeamList(List<Team> teamList) {
    this.teamList = teamList;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
    if (!(object instanceof Trainer)) {
      return false;
    }
    Trainer other = (Trainer) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.siewares.vfl.entity.Trainer[ id=" + id + " ]";
  }
  
}

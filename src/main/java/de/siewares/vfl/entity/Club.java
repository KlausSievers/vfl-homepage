/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Klaus
 */
@Entity
@Table(name = "club")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Club.findAll", query = "SELECT c FROM Club c")})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "clubId")
public class Club implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  @JsonProperty("clubId")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "name")
  private String name;
  @Size(max = 45)
  @Column(name = "logo_path")
  private String logoPath;
  @Size(max = 80)
  @Column(name = "webside")
  private String webside;
  @Size(max = 45)
  @Column(name = "mail")
  private String mail;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "address")
  private String address;
  @Basic(optional = false)
  @NotNull
  @Column(name = "postcode")
  private int postcode;
  @Size(max = 45)
  @Column(name = "city")
  private String city;
  @Column(name = "county")
  private Integer county;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "opponent")
  @JsonIgnore
  private List<Match> matchList;

  public Club() {
  }

  public Club(Integer id) {
    this.id = id;
  }

  public Club(Integer id, String name, String address, int postcode) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.postcode = postcode;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogoPath() {
    return logoPath;
  }

  public void setLogoPath(String logoPath) {
    this.logoPath = logoPath;
  }

  public String getWebside() {
    return webside;
  }

  public void setWebside(String webside) {
    this.webside = webside;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getPostcode() {
    return postcode;
  }

  public void setPostcode(int postcode) {
    this.postcode = postcode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getCounty() {
    return county;
  }

  public void setCounty(Integer county) {
    this.county = county;
  }

  @XmlTransient
  public List<Match> getMatchList() {
    return matchList;
  }

  public void setMatchList(List<Match> matchList) {
    this.matchList = matchList;
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
    if (!(object instanceof Club)) {
      return false;
    }
    Club other = (Club) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.siewares.vfl.entity.Club[ id=" + id + " ]";
  }
  
}

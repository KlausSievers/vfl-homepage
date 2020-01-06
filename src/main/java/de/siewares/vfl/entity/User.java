/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Klaus
 */
@Entity
@Table(name = "user")
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "username")
  private String username;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 60)
  @Column(name = "password")
  @JsonIgnore
  private String password;
  @Basic(optional = false)
  @NotNull
  @Column(name = "enabled")
  private short enabled;

  @Size(min = 1, max = 45)
  @Column(name = "name")
  private String name;
  @Size(min = 1, max = 45)
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "date_of_birth")
  @Temporal(TemporalType.DATE)
  private Date dateOfBirth;
  @Size(max = 45)
  @Column(name = "image_path")
  private String imagePath;
  @Size(min = 1, max = 45)
  @Column(name = "address")
  private String address;
  @Column(name = "postcode")
  private int postcode;
  @Size(min = 1, max = 45)
  @Column(name = "city")
  private String city;
  @Size(max = 45)
  @Column(name = "number_private")
  private String numberPrivate;
  @Size(max = 45)
  @Column(name = "number_mobile")
  private String numberMobile;
  @Size(max = 45)
  @Column(name = "number_work")
  private String numberWork;
  @Size(max = 45)
  @Column(name = "mail")
  @JsonIgnore
  private String mail;

  public User() {
  }

  public User(Integer id) {
    this.id = id;
  }

  public User(Integer id, String username, String password, short enabled) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public short getEnabled() {
    return enabled;
  }

  public void setEnabled(short enabled) {
    this.enabled = enabled;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
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

  public String getNumberPrivate() {
    return numberPrivate;
  }

  public void setNumberPrivate(String numberPrivate) {
    this.numberPrivate = numberPrivate;
  }

  public String getNumberMobile() {
    return numberMobile;
  }

  public void setNumberMobile(String numberMobile) {
    this.numberMobile = numberMobile;
  }

  public String getNumberWork() {
    return numberWork;
  }

  public void setNumberWork(String numberWork) {
    this.numberWork = numberWork;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public boolean isHasMail() {
    return this.mail != null;
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
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return this.firstName+" "+this.name;
  }

}

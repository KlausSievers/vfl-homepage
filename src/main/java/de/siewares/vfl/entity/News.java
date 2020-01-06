/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Klaus
 */
@Entity
@Table(name = "news")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class News implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "headline")
  private String headline;
  @ManyToOne
  @JoinColumn(name = "editor", referencedColumnName = "id")
  private User editor;
  @Basic(optional = false)
  @NotNull
  @Column(name = "insert_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date insertDate;
  @Lob
  @Size(max = 65535)
  @Column(name = "text")
  private String text;
  @JoinColumn(name = "category", referencedColumnName = "id")
  @ManyToOne(optional = false)
  @JsonBackReference(value = "news_category")
  private Category category;

  public News() {
  }

  public News(Integer id) {
    this.id = id;
  }

  public News(Integer id, String headline, Date date) {
    this.id = id;
    this.headline = headline;
    this.insertDate = date;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getHeadline() {
    return headline;
  }

  public void setHeadline(String headline) {
    this.headline = headline;
  }

  public User getEditor() {
    return editor;
  }

  public void setEditor(User editor) {
    this.editor = editor;
  }

  public Date getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(Date insertDate) {
    this.insertDate = insertDate;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
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
    if (!(object instanceof News)) {
      return false;
    }
    News other = (News) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.siewares.vfl.entity.News[ id=" + id + " ]";
  }

}

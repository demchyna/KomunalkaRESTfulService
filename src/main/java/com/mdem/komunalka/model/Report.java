package com.mdem.komunalka.model;

import com.mdem.komunalka.model.common.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report implements IEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  private java.sql.Date date;
  private String description;
  private Long category_id;
  private Long user_id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getCategory_id() {
    return category_id;
  }

  public void setCategory_id(Long category_id) {
    this.category_id = category_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }
}

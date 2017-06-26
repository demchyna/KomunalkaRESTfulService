package com.mdem.komunalka.model;

import com.mdem.komunalka.model.common.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "tariff")
public class Tariff implements IEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  private String nane;
  private String currency;
  private java.sql.Date begin_date;
  private String end_date;
  private String description;
  private Long category_id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNane() {
    return nane;
  }

  public void setNane(String nane) {
    this.nane = nane;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public java.sql.Date getBegin_date() {
    return begin_date;
  }

  public void setBegin_date(java.sql.Date begin_date) {
    this.begin_date = begin_date;
  }

  public String getEnd_date() {
    return end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
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
}

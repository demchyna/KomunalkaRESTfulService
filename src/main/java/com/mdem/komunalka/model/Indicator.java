package com.mdem.komunalka.model;

import com.mdem.komunalka.model.common.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "indicator")
public class Indicator implements IEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  private Long previous;
  private Long current;
  private java.sql.Date date;
  private String description;

  @ManyToOne
  @JoinColumn(name = "meter_id")
  private Meter meter_id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPrevious() {
    return previous;
  }

  public void setPrevious(Long previous) {
    this.previous = previous;
  }

  public Long getCurrent() {
    return current;
  }

  public void setCurrent(Long current) {
    this.current = current;
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

  public Meter getMeter_id() {
    return meter_id;
  }

  public void setMeter_id(Meter meter_id) {
    this.meter_id = meter_id;
  }
}

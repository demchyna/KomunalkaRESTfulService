package com.mdem.komunalka.models;

public class Indicator {
  private Long id;
  private Long previous;
  private Long current;
  private java.sql.Date date;
  private String description;
  private Long unit_id;
  private Long meter_id;

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

  public Long getUnit_id() {
    return unit_id;
  }

  public void setUnit_id(Long unit_id) {
    this.unit_id = unit_id;
  }

  public Long getMeter_id() {
    return meter_id;
  }

  public void setMeter_id(Long meter_id) {
    this.meter_id = meter_id;
  }
}

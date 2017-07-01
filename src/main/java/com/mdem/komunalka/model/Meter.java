package com.mdem.komunalka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdem.komunalka.model.common.IEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meter")
public class Meter implements IEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  private String name;
  private String description;
  private Long unit_id;
  private Long category_id;

  @OneToMany(mappedBy = "meter_id")
  @JsonIgnore
  private List<Indicator> indicators;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Long getCategory_id() {
    return category_id;
  }

  public void setCategory_id(Long category_id) {
    this.category_id = category_id;
  }

  public List<Indicator> getIndicators() {
    return indicators;
  }

  public void setIndicators(List<Indicator> indicators) {
    this.indicators = indicators;
  }
}

package com.mdem.komunalka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdem.komunalka.model.common.IEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category_id")
    @JsonIgnore
    private List<Tariff> tariffs;

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

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }
}

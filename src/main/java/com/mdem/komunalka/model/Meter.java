package com.mdem.komunalka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdem.komunalka.model.common.IEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "meter")
public class Meter implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Zа-яА-ЯіІїЇєЄ]+.*")
    @Size(min = 2, max = 31)
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    @NotNull
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @OneToMany(mappedBy = "meter")
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

    public Unit getUnit() {
      return unit;
    }

    public void setUnit(Unit unit) {
      this.unit = unit;
    }

    public Category getCategory() {
      return category;
    }

    public void setCategory(Category category) {
      this.category = category;
    }

    public List<Indicator> getIndicators() {
      return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
      this.indicators = indicators;
    }
}

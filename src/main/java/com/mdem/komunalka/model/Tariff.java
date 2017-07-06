package com.mdem.komunalka.model;

import com.mdem.komunalka.model.common.IEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tariff")
public class Tariff implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String currency;
    private java.math.BigDecimal price;
    private java.sql.Date begin_date;
    private String end_date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_id;

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

    public String getCurrency() {
      return currency;
    }

    public void setCurrency(String currency) {
      this.currency = currency;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(BigDecimal price) {
      this.price = price;
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

    public Category getCategory_id() {
      return category_id;
    }

    public void setCategory_id(Category category_id) {
      this.category_id = category_id;
    }
}

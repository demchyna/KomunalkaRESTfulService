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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public Category getCategory() {
    return category;
  }

    public void setCategory(Category category) {
    this.category = category;
  }

    public User getUser() {
    return user;
  }

    public void setUser(User user) {
    this.user = user;
  }
}

package com.mdem.komunalka.model;

import com.mdem.komunalka.model.common.IEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "indicator")
public class Indicator implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long current;
    private java.sql.Date date;
    private Boolean status;
    private String description;

    //@ManyToOne
    //@JoinColumn(name = "previous_id", referencedColumnName = "id")
    //private Indicator previousId;

    @Column(name = "previous_id")
    private Long previousId;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Long previousId) {
        this.previousId = previousId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
    return description;
  }

    public void setDescription(String description) {
    this.description = description;
  }

    public Meter getMeter() {
    return meter;
  }

    public void setMeter(Meter meter) {
    this.meter = meter;
  }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}

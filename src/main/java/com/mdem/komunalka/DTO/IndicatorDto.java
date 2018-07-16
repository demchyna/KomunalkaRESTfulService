package com.mdem.komunalka.DTO;

import java.sql.Date;

public class IndicatorDto {
    private Long id;
    private Long current;
    private java.sql.Date date;
    private Boolean status;
    private String description;
    private Long previous;
    private Long meterId;

    public IndicatorDto() {
    }

    public IndicatorDto(Long id, Long current, Date date, Boolean status, String description, Long previous, Long meterId) {
        this.id = id;
        this.current = current;
        this.date = date;
        this.status = status;
        this.description = description;
        this.previous = previous;
        this.meterId = meterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Long getPrevious() {
        return previous;
    }

    public void setPrevious(Long previous) {
        this.previous = previous;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }
}

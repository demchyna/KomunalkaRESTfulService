package com.mdem.komunalka.DTO;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

public class IndicatorDto {
    private Long id;

    @NotNull
    @Range(min = 1L, max = 9999999999L)
    private Long current;
    @NotNull private java.sql.Date date;
    @NotNull private Boolean status;
    private String description;
    private Long previous;
    private Long previousId;
    @NotNull private Long meterId;
    @NotNull private Long tariffId;
    private BigDecimal price;
    @NotNull private Long userId;

    public IndicatorDto() {
    }

    public IndicatorDto(Long id, Long current, Date date, Boolean status, String description, Long previous, Long previousId, Long meterId, Long tariffId, BigDecimal price, Long userId) {
        this.id = id;
        this.current = current;
        this.date = date;
        this.status = status;
        this.description = description;
        this.previous = previous;
        this.previousId = previousId;
        this.meterId = meterId;
        this.tariffId = tariffId;
        this.price = price;
        this.userId = userId;
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

    public Long getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Long previousId) {
        this.previousId = previousId;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

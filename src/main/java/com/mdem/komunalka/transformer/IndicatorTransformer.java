package com.mdem.komunalka.transformer;

import com.mdem.komunalka.DTO.IndicatorDto;
import com.mdem.komunalka.model.Indicator;
import com.mdem.komunalka.model.Meter;
import com.mdem.komunalka.service.impl.IndicatorService;
import com.mdem.komunalka.service.impl.MeterService;

public class IndicatorTransformer {

    public static IndicatorDto toIndicatorDto(Indicator currentIndicator, Long previousIndicator) {
        return new IndicatorDto(
                currentIndicator.getId(),
                currentIndicator.getCurrent(),
                currentIndicator.getDate(),
                currentIndicator.getStatus(),
                currentIndicator.getDescription(),
                previousIndicator,
                currentIndicator.getMeter().getId()
        );
    }

    public static Indicator toIndicatorEntity(IndicatorDto indicatorDto, Meter meter) {
        Indicator indicator = new Indicator();

        if (indicatorDto.getId() != null) {
            indicator.setId(indicatorDto.getId());
        }
        indicator.setCurrent(indicatorDto.getCurrent());
        indicator.setDate(indicatorDto.getDate());
        indicator.setStatus(indicatorDto.getStatus());
        indicator.setDescription(indicatorDto.getDescription());
        indicator.setPreviousId(indicatorDto.getPrevious());
        indicator.setMeter(meter);

        return indicator;
    }
}

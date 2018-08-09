package com.mdem.komunalka.transformer;

import com.mdem.komunalka.DTO.IndicatorDto;
import com.mdem.komunalka.model.Indicator;
import com.mdem.komunalka.model.Meter;
import com.mdem.komunalka.model.Tariff;
import com.mdem.komunalka.service.impl.IndicatorService;
import com.mdem.komunalka.service.impl.MeterService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class IndicatorTransformer {

    public static IndicatorDto toIndicatorDto(Indicator currentIndicator, Indicator previousIndicator) {

        Long previousValue = 0L;
        Long previousId = null;

        if (previousIndicator != null) {
            previousValue = previousIndicator.getCurrent();
            previousId = previousIndicator.getId();
        }

        return new IndicatorDto(
                currentIndicator.getId(),
                currentIndicator.getCurrent(),
                currentIndicator.getDate(),
                currentIndicator.getStatus(),
                currentIndicator.getDescription(),
                previousValue,
                previousId,
                currentIndicator.getMeter().getId(),
                currentIndicator.getTariff().getId(),
                calculatePrice(currentIndicator, previousValue),
                currentIndicator.getMeter().getCategory().getUser().getId()
        );
    }

    public static Indicator toIndicatorEntity(IndicatorDto indicatorDto, Meter meter, Tariff tariff) {
        Indicator indicator = new Indicator();

        if (indicatorDto.getId() != null) {
            indicator.setId(indicatorDto.getId());
        }
        indicator.setCurrent(indicatorDto.getCurrent());
        indicator.setDate(indicatorDto.getDate());
        indicator.setStatus(indicatorDto.getStatus());
        indicator.setDescription(indicatorDto.getDescription());
        indicator.setPreviousId(indicatorDto.getPreviousId());
        indicator.setMeter(meter);
        indicator.setTariff(tariff);

        return indicator;
    }

    private static BigDecimal calculatePrice(Indicator currentIndicator, Long previousIndicator) {
        return BigDecimal.valueOf(currentIndicator.getCurrent() - previousIndicator)
                .multiply(currentIndicator.getTariff().getRate());
    }
}

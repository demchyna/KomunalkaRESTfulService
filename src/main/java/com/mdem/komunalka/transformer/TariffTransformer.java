package com.mdem.komunalka.transformer;

import com.mdem.komunalka.DTO.TariffDto;
import com.mdem.komunalka.model.Category;
import com.mdem.komunalka.model.Tariff;
import com.mdem.komunalka.model.Unit;

public class TariffTransformer {
    public static TariffDto toTariffDto(Tariff tariff) {
        return new TariffDto(
                tariff.getId(),
                tariff.getName(),
                tariff.getCurrency(),
                tariff.getRate(),
                tariff.getBeginDate(),
                tariff.getEndDate(),
                tariff.getDescription(),
                tariff.getCategory().getId(),
                tariff.getUnit().getId(),
                tariff.getUnit().getName()
        );
    }

    public static Tariff toTariffEntity(TariffDto tariffDto, Category category, Unit unit) {
        Tariff tariff = new Tariff();

        if (tariffDto.getId() != null) {
            tariff.setId(tariffDto.getId());
        }
        tariff.setName(tariffDto.getName());
        tariff.setCurrency(tariffDto.getCurrency());
        tariff.setRate(tariffDto.getRate());
        tariff.setBeginDate(tariffDto.getBeginDate());
        tariff.setEndDate(tariffDto.getEndDate());
        tariff.setDescription(tariffDto.getDescription());
        tariff.setCategory(category);
        tariff.setUnit(unit);

        return tariff;
    }
}

package com.github.arkoski.dto;

import com.github.arkoski.model.SkiResort;

import java.math.BigDecimal;

public class SkiResortDto {

    private String city;
    private BigDecimal price;

    public SkiResortDto() {
    }

    public SkiResortDto(SkiResort skiResort) {
        this.city = skiResort.getCity();
        this.price = skiResort.getPrice();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

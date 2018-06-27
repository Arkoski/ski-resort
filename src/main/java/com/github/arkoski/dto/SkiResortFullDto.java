package com.github.arkoski.dto;

import com.github.arkoski.model.SkiResort;

import java.math.BigDecimal;

public class SkiResortFullDto {

    private String city;
    private BigDecimal price;
    private Integer humidity;
    private Integer pressure;
    private BigDecimal temperature;

    public SkiResortFullDto() {
    }

    public SkiResortFullDto(SkiResort skiResort) {
        this.city = skiResort.getCity();
        this.price = skiResort.getPrice();
//        this.humidity = skiResort.getHumidity();
//        this.pressure = skiResort.getPressure();
//        this.temperature = skiResort.getTemperature();
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

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }
}

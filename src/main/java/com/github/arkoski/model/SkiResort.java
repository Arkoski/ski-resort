package com.github.arkoski.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class SkiResort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private BigDecimal price;

    @OneToMany(mappedBy = "skiResort")
    private Set<SkiResortWeather> skiResortWeather;

    @Enumerated(EnumType.STRING)
    private WeatherCondition weatherCondition;

    public SkiResort(String city, BigDecimal price, Set<SkiResortWeather> skiResortWeather, WeatherCondition weatherCondition) {
        this.city = city;
        this.price = price;
        this.skiResortWeather = skiResortWeather;
        this.weatherCondition = weatherCondition;
    }

    public SkiResort() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<SkiResortWeather> getSkiResortWeather() {
        return skiResortWeather;
    }

    public void setSkiResortWeather(Set<SkiResortWeather> skiResortWeather) {
        this.skiResortWeather = skiResortWeather;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}

package com.github.arkoski.model;

public enum WeatherCondition {
    GOOD("Good"),
    AVERAGE("Average"),
    BAD("Bad");

    private final String value;
    WeatherCondition(String weatherCondition){
        this.value = weatherCondition;
    }

    public String getValue() {
        return value;
    }
}

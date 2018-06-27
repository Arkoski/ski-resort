package com.github.arkoski.service;

import com.github.arkoski.dto.SkiResortDto;
import com.github.arkoski.model.SkiResort;
import com.github.arkoski.model.SkiResortWeather;

public interface SkiResortWeatherService {

    SkiResortWeather createSkiResortWeather(String city);
}

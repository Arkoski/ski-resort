package com.github.arkoski.repository;

import com.github.arkoski.model.SkiResortWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiResortWeatherRepository extends JpaRepository<SkiResortWeather,Long>{


}

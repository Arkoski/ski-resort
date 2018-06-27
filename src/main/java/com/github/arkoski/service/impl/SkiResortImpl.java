package com.github.arkoski.service.impl;

import com.github.arkoski.dto.SkiResortDto;
import com.github.arkoski.exceptions.ValidationError;
import com.github.arkoski.exceptions.ValidationException;
import com.github.arkoski.model.SkiResort;
import com.github.arkoski.model.SkiResortWeather;
import com.github.arkoski.repository.SkiResortRepository;
import com.github.arkoski.service.SkiResortService;
import com.github.arkoski.service.SkiResortWeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class SkiResortImpl implements SkiResortService {
    private SkiResortRepository skiResortRepository;
    private SkiResortWeatherService skiResortWeatherService;

    @Autowired
    public SkiResortImpl(SkiResortRepository skiResortRepository, SkiResortWeatherService skiResortWeatherService) {
        this.skiResortRepository = skiResortRepository;
        this.skiResortWeatherService = skiResortWeatherService;
    }


    @Override
    public SkiResort findById(Long id) {
        return skiResortRepository.findById(id).orElseThrow(() -> new ValidationException(validateFindById(id)));
    }

    @Override
    public Set<SkiResort> findAll() {
        return new HashSet<>(skiResortRepository.findAll());
    }

    @Override
    public SkiResort createSkiResort(SkiResortDto resort) {
        validateCreation(resort.getCity(), resort.getPrice());

        SkiResort skiResort = new SkiResort();
        skiResort.setCity(resort.getCity());
        skiResort.setPrice(resort.getPrice());

        SkiResort save = skiResortRepository.save(skiResort);
        skiResortWeatherService.createSkiResortWeather(save.getCity());
        return save;
    }

    @Override
    public void deleteSkiResort(Long id) {
        try {
            skiResortRepository.deleteById(id);
        } catch (RuntimeException e) {
            validateDeleteById(id);
        }
    }

    private void validateCreation(String city, BigDecimal price) {
        List<ValidationError> errors = new ArrayList<>();
        if (city == null) {
            ValidationError error = new ValidationError("city", "May not be null");
            errors.add(error);
        } else if (city.isEmpty()) {
            ValidationError error = new ValidationError("city", "May not be null");
            errors.add(error);
        }

        if (price == null) {
            ValidationError error = new ValidationError("price", "May not be null");
            errors.add(error);
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    private List<ValidationError> validateFindById(Long id) {
        List<ValidationError> errors = new ArrayList<>();

        ValidationError error = new ValidationError("id", "Wrong id");
        errors.add(error);

        return errors;
    }

    private void validateDeleteById(Long id) {
        List<ValidationError> errors = new ArrayList<>();

        ValidationError error = new ValidationError("Id", "Wrong id");
        errors.add(error);


        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}

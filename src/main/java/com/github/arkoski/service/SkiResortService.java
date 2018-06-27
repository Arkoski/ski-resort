package com.github.arkoski.service;

import com.github.arkoski.dto.SkiResortDto;
import com.github.arkoski.model.SkiResort;

import java.util.Set;

public interface SkiResortService {

    SkiResort createSkiResort(SkiResortDto resort);
    void deleteSkiResort(Long id);
    Set<SkiResort> findAll();
    SkiResort findById(Long Id);
}

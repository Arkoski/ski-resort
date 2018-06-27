package com.github.arkoski.controller;

import com.github.arkoski.dto.SkiResortDto;
import com.github.arkoski.dto.SkiResortFullDto;
import com.github.arkoski.model.SkiResort;
import com.github.arkoski.service.SkiResortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api/ski-resort")
public class SkiResortController {


    private SkiResortService skiResortService;

    @Autowired
    SkiResortController(SkiResortService skiResortService) {
        this.skiResortService = skiResortService;
    }

    @GetMapping("/{id}")
    public SkiResortFullDto findOneById(@PathVariable Long id){
        SkiResort resort = skiResortService.findById(id);
        return new SkiResortFullDto(resort);
    }

    @PostMapping
    public SkiResortDto create(@RequestBody SkiResortDto resort){
        SkiResort skiResort = skiResortService.createSkiResort(resort);
        return new SkiResortDto(skiResort);
    }


    @DeleteMapping("/{id}")
    public void deleteSkiResort(@PathVariable Long id){
        skiResortService.deleteSkiResort(id);
    }


    @GetMapping
    public Set<SkiResortFullDto> findAll(){
        Set<SkiResortFullDto> result = new HashSet<>();

        Set<SkiResort> all = skiResortService.findAll();
        all.forEach(b -> result.add(new SkiResortFullDto(b)));

        return result;
    }





}

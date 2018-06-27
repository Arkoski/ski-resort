package com.github.arkoski.repository;

import com.github.arkoski.model.SkiResort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiResortRepository extends JpaRepository <SkiResort, Long> {

    SkiResort findOneByCity(String city);

}




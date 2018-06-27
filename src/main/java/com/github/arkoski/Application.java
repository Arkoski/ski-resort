package com.github.arkoski;
import com.github.arkoski.model.SkiResort;
import com.github.arkoski.repository.SkiResortRepository;
import com.github.arkoski.service.SkiResortWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
public class Application {
    private SkiResortRepository skiResortRepository;
    private SkiResortWeatherService skiResortWeatherService;

    @Autowired
    public Application(SkiResortRepository skiResortRepository, SkiResortWeatherService skiResortWeatherService) {
        this.skiResortRepository = skiResortRepository;
        this.skiResortWeatherService = skiResortWeatherService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("greetings")
                .select()
                .paths(regex("/api.*"))
                .build();
    }


    @Scheduled(fixedRate = 300000L)
    //@Scheduled(cron = "0 * * * * ?")
    public void scheduled(){
        Set<SkiResort> allSkiResort = new HashSet<>(skiResortRepository.findAll());
        allSkiResort.forEach(s->skiResortWeatherService.createSkiResortWeather(s.getCity()));
        System.out.println("Zaktualizowalem pogode");
    }

}

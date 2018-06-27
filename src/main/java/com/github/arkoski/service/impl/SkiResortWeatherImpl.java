package com.github.arkoski.service.impl;

import com.github.arkoski.model.SkiResort;
import com.github.arkoski.model.SkiResortWeather;
import com.github.arkoski.repository.SkiResortRepository;
import com.github.arkoski.repository.SkiResortWeatherRepository;
import com.github.arkoski.service.SkiResortWeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class SkiResortWeatherImpl implements SkiResortWeatherService {
    private SkiResortRepository skiResortRepository;
    private SkiResortWeatherRepository skiResortWeatherRepository;

    @Autowired
    public SkiResortWeatherImpl(SkiResortRepository skiResortRepository, SkiResortWeatherRepository skiResortWeatherRepository) {
        this.skiResortRepository = skiResortRepository;
        this.skiResortWeatherRepository = skiResortWeatherRepository;
    }

    @Override
    public SkiResortWeather createSkiResortWeather(String city) {
        SkiResortWeather skiResortWeather = new SkiResortWeather();

        String link = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=e0d1bd5c6709a5d3cebb23193dcd1d92";

        String res = jsonGetRequest(link);
        JSONObject jsonObject = new JSONObject(res);
        jsonObject = jsonObject.getJSONObject("main");

        Number val = jsonObject.getNumber("temp");
        Number pre = jsonObject.getNumber("pressure");
        Number hum = jsonObject.getNumber("humidity");

        BigDecimal temp = BigDecimal.valueOf(Math.round(val.doubleValue() - 273.15));
        Integer pressure = pre.intValue();
        Integer humidity = hum.intValue();

        skiResortWeather.setTemperature(temp);
        skiResortWeather.setPressure(pressure);
        skiResortWeather.setHumidity(humidity);
        skiResortWeather.setCity(city);

        SkiResort skiResort = skiResortRepository.findOneByCity(city);
        skiResortWeather.setSkiResort(skiResort);

        return skiResortWeatherRepository.save(skiResortWeather);

    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}

package com.example.demo.service;

import com.example.demo.entity.Weather;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    public Weather getWeather(String city) throws IOException {

        ResponseEntity<String> output2 = null;
        try {

            output2 = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=cacdc35eaf804f98d919aba65f7630c9&units=metric", String.class);

            System.out.println(output2.getHeaders());

            System.out.println(output2.getBody());
            System.out.println(output2.getStatusCode());


        }catch (Exception e){
//            if (e.getMessage().equals("404 Not Found: \"{\"cod\":\"404\",\"message\":\"city not found\"}\""))
                return null;
//            System.out.println(e.getMessage());
        }
        assert output2 != null;

        ObjectMapper objectMapper = new ObjectMapper();

        Map weatherMap =  objectMapper.readValue(output2.getBody(), Map.class);
        Map main = (Map) weatherMap.get("main");
        Map wind = (Map) weatherMap.get("wind");
        Map clouds = (Map) weatherMap.get("clouds");
        Map rain = (Map) weatherMap.get("rain");
        Map snow = (Map) weatherMap.get("snow");

        Weather weather = new Weather();
        weather.setTemp(Float.parseFloat( main.get("temp").toString()));
        weather.setPressure(Integer.parseInt( main.get("pressure").toString()));
        weather.setHumidity(Integer.parseInt(main.get("humidity").toString()));
        weather.setVisibility(Integer.parseInt(weatherMap.get("visibility").toString()));
        weather.setWind(Float.parseFloat(wind.get("speed").toString()));
        weather.setClouds(Integer.parseInt(clouds.get("all").toString()));
        weather.setName(city);

        if ( rain != null)
            weather.setRain(Float.parseFloat(rain.get("1h").toString()));

        if ( snow != null)
            weather.setSnow(Float.parseFloat(snow.get("1h").toString()));

        return weather;

    }

}

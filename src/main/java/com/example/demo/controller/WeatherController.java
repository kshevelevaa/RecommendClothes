package com.example.demo.controller;

import com.example.demo.entity.Weather;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/getWeather/{city}")
    public Weather getWeather(@PathVariable String city) throws IOException {
//        User userAuth = userService.getUserAuth();
       return weatherService.getWeather(city);

    }
}

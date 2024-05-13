package com.example.demo.controller;

import com.example.demo.entity.Weather;
import com.example.demo.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/getWeather/{city}")
    public Weather getWeather(@PathVariable String city) throws IOException {
//        User userAuth = userService.getUserAuth();
       return weatherService.getWeather(city);
    }
}

package com.example.demo.entity;

//import javax.persistence.*;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private  String name;
    private float temp;
    private int pressure;
    private float wind;
    private int humidity;
    private int visibility;
    private int clouds;
    private float rain;
    private float snow;

}

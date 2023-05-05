package com.template.data.DTO;

import com.template.data.enums.WeatherTypeEnum;

import java.time.LocalDate;

public record MeteorologicalInfoDTO (
        String city,
        LocalDate weatherDate,
        WeatherTypeEnum morningWeather ,
        WeatherTypeEnum nightWeather,
        int maxTemperature,
        int minTemperature,
        int precipitaion,
        int humidity,
        int windSpeed
){

}



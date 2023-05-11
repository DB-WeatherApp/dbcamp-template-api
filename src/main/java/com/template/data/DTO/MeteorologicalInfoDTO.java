package com.template.data.DTO;

import com.template.data.enums.WeatherTypeEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record MeteorologicalInfoDTO (
        @NotBlank
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



package com.template.data.enums;

public enum WeatherTypeEnum {

    RAINY("Chuvoso"),
    STORMY("Tempestuoso"),
    SUNNY("Esolarado"),
    CLOUDY("Nublado"),

    SUNNY_CLOUDY("Sol e Nuvens");


    private final String description;

    WeatherTypeEnum(String description){
        this.description = description;
    }


}

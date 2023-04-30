package com.template.data.entity;

import com.template.data.enums.WeatherTypeEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "meteorological_info")
public class MeteorologicalInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="city")
    private String city;
    @Column(name = "weather_date")
    private Date weatherDate;

    @Column(name="morning_weather")
    private WeatherTypeEnum morningWeather;
    @Column(name="night_weather")
    private WeatherTypeEnum nightWeather;

    @Column(name = "max_temperature")
    private int maxTemperature;
    @Column(name = "min_temperature")
    private int minTemperature;
    @Column(name = "precipitation ")
    private int precipitation;
    @Column(name = "humidity")
    private int humidity;
    @Column(name = "wind_speed")
    private int windSpeed;

    public MeteorologicalInfoEntity(
            long id,
            String city,
            Date weatherDate,
            WeatherTypeEnum morningWeather,
            WeatherTypeEnum nightWeather,
            int maxTemperature,
            int minTemperature,
            int precipitation,
            int humidity,
            int windSpeed
    ){
        this.id = id;
        this.city = city;
        this.weatherDate = weatherDate;
        this.morningWeather = morningWeather;
        this.nightWeather = nightWeather;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public long getId() {
        return id;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }

    public Date getWeatherDate(){
        return weatherDate;
    }

    public void setWeatherDate(Date weatherDate){
        this.weatherDate = weatherDate;
    }

    public WeatherTypeEnum getMorningWeather(){
        return morningWeather;
    }
    public void setMorningWeather(WeatherTypeEnum morningWeather){
        this.morningWeather = morningWeather;
    }

    public WeatherTypeEnum getNightWeather(){
        return nightWeather;
    }

    public void setNightWeather(WeatherTypeEnum nightWeather){
        this.nightWeather = nightWeather;
    }

    public int getMaxTemperature(){
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature){
        this.maxTemperature = maxTemperature;
    }

    public int getMinTemperature(){
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature){
        this.minTemperature = minTemperature
    }

    public int getPrecipitaion(){
        return precipitation;
    }

    public void setPrecipitaion(int precipitaion){
        this.precipitation = precipitaion;
    }


    public int getHumidity(){
        return humidity;
    }

    public void setHumidity(int humidity){
        this.humidity = humidity;
    }

    public int getWindSpeed(){
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed){
        this.windSpeed = windSpeed;
    }



}

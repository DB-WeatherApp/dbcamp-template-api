package com.template.data.entity;

import com.template.data.enums.WeatherTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "meteorological_info")
public class MeteorologicalInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String city;
    @Column(name = "weather_date")
    private LocalDate weatherDate;
    @Column(name="morning_weather")
    private WeatherTypeEnum morningWeather;
    @Column(name="night_weather")
    private WeatherTypeEnum nightWeather;
    @Column(name = "max_temperature")
    private Integer maxTemperature;
    @Column(name = "min_temperature")
    private Integer minTemperature;
    private Integer precipitation;
    private Integer humidity;
    @Column(name = "wind_speed")
    private Integer windSpeed;

    public MeteorologicalInfoEntity(
            long id,
            String city,
            LocalDate weatherDate,
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
    public MeteorologicalInfoEntity(){
    };

    public long getId() {
        return id;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }

    public LocalDate getWeatherDate(){
        return weatherDate;
    }

    public void setWeatherDate(LocalDate weatherDate){
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
        this.minTemperature = minTemperature;
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

    public void atualizarinformacoes(MeteorologicalInfoEntity meteorologicalEntity) {
        if(meteorologicalEntity.city != null) {
            this.city = meteorologicalEntity.getCity();
        }
        if(meteorologicalEntity.weatherDate != null){
            this.weatherDate = meteorologicalEntity.weatherDate;
        }
        if(meteorologicalEntity.morningWeather !=null){
            this.morningWeather = meteorologicalEntity.morningWeather;
        }
        if(meteorologicalEntity.nightWeather != null){
            this.nightWeather = meteorologicalEntity.nightWeather;
        }
        if(meteorologicalEntity.maxTemperature != null){
            this.maxTemperature = meteorologicalEntity.maxTemperature;
        }
        if(meteorologicalEntity.minTemperature != null){
            this.minTemperature = meteorologicalEntity.minTemperature;
        }
        if(meteorologicalEntity.precipitation != null){
            this.precipitation = meteorologicalEntity.precipitation;
        }
        if(meteorologicalEntity.humidity !=null){
            this.humidity = meteorologicalEntity.humidity;
        }
        if(meteorologicalEntity.windSpeed !=null){
            this.windSpeed = meteorologicalEntity.windSpeed;
        }
    }
}

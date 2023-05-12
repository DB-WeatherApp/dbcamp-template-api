package com.template.Exception;

public class MeteorologicalInfoNotFound extends RuntimeException{
    public MeteorologicalInfoNotFound(String message) {
        super(message);
    }
}

package com.template.Exception.Errors;

import com.template.Exception.MeteorologicalInfoNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class ResourceExeptionHandler {
    @ExceptionHandler(MeteorologicalInfoNotFound.class)
    public ResponseEntity<StardardError>meteorologicalInfoNotFound(MeteorologicalInfoNotFound ex, HttpServletRequest request){
        StardardError error = new StardardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}

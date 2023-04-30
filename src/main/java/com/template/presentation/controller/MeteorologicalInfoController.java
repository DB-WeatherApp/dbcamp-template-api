package com.template.presentation.controller;

import com.template.business.services.MeteorologicalInfoService;
import com.template.data.entity.MeteorologicalInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4767")
@RestController
@RequestMapping("/api")
public class MeteorologicalInfoController {

    @Autowired
    MeteorologicalInfoService service;

    @PostMapping("/meteorologicalInfo")
    public ResponseEntity<MeteorologicalInfoEntity> createTutorial(@RequestBody MeteorologicalInfoEntity meteorologicalInfo) {
        try {
            MeteorologicalInfoEntity metInfo = service.create(meteorologicalInfo);
            return new ResponseEntity<>(metInfo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
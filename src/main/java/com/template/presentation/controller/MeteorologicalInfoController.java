package com.template.presentation.controller;

import com.template.business.services.MeteorologicalInfoService;
import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.enums.WeatherTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:4767")
@RestController
@RequestMapping("/api/meteorologicalInfo")
public class MeteorologicalInfoController {

    @Autowired
    MeteorologicalInfoService service;

    @PostMapping
    public ResponseEntity<MeteorologicalInfoEntity> createMeteorologicalData(@RequestBody MeteorologicalInfoEntity meteorologicalInfoEntity) {
            MeteorologicalInfoEntity metInfo = service.createMeteorologicalInfo(meteorologicalInfoEntity);
            return new ResponseEntity<MeteorologicalInfoEntity>(metInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MeteorologicalInfoEntity>> getAllMeteorologicalInfo(@PageableDefault(
            size=10,
            sort = {"weatherDate"},
            direction = Sort.Direction.DESC
    )
            Pageable page
    ){
        try{
            Page<MeteorologicalInfoEntity> metInfo = service.getAll(page);
            if (metInfo.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(metInfo,HttpStatus.OK);
        }catch (Exception error){
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeteorologicalInfoEntity> findMeteorologicalDataID(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMeteorologicalInfo(@PathVariable Long id) {
        service.deleteMeteorologicalInfoById(id);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody MeteorologicalInfoEntity metInfo){
        service.editMeteorologicalInfo(metInfo);
    }

}
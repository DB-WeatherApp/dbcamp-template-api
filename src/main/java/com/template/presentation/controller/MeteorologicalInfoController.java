package com.template.presentation.controller;

import com.template.business.services.MeteorologicalInfoService;
import com.template.data.DTO.MeteorologicalInfoDTO;
import com.template.data.entity.MeteorologicalInfoEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4767")
@RestController
@RequestMapping("/api/meteorologicalInfo")
public class MeteorologicalInfoController {

    @Autowired
    MeteorologicalInfoService service;

    @PostMapping
    public ResponseEntity<MeteorologicalInfoEntity> createMeteorologicalData(@RequestBody MeteorologicalInfoDTO meteorologicalInfoDTO) {
        try {
            MeteorologicalInfoEntity metInfo = service.create(meteorologicalInfoDTO);
            return new ResponseEntity<MeteorologicalInfoEntity>(metInfo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMeteorologicalInfo(@PathVariable Long id) {
        service.deleteById(id);
    }
}
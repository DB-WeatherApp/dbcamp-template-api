package com.template.data.repository;

import com.template.data.entity.MeteorologicalInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MeteorologicalInfoRepository extends JpaRepository<MeteorologicalInfoEntity, Long> {
    Page<MeteorologicalInfoEntity> findByCity(String city, Pageable page);
}
package com.template.data.repository;

import com.template.data.entity.MeteorologicalInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeteorologicalInfoRepository extends JpaRepository<MeteorologicalInfoEntity, Long> {

}
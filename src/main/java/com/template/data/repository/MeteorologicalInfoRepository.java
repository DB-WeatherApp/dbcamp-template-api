package com.template.data.repository;

import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.entity.TutorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeteorologicalInfoRepository extends JpaRepository<TutorialEntity, Long> {
    List<MeteorologicalInfoEntity> findByPublished(String city);
}
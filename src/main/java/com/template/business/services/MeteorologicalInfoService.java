package com.template.business.services;

import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.repository.MeteorologicalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeteorologicalInfoService {


    private MeteorologicalInfoRepository repository;

    @Autowired
    public MeteorologicalInfoService(MeteorologicalInfoRepository repository){
        this.repository = repository;
    }

    public MeteorologicalInfoEntity create(MeteorologicalInfoEntity meteorologicalInfoEntity){
        return repository.save(meteorologicalInfoEntity);
    }


}

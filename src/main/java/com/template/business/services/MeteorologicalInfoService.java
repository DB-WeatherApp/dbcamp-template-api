package com.template.business.services;

import com.template.Exception.MissingParameterException;
import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.repository.MeteorologicalInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class MeteorologicalInfoService {

    private MeteorologicalInfoRepository repository;

    @Autowired
    public MeteorologicalInfoService(MeteorologicalInfoRepository repository){
        this.repository = repository;
    }

    public Page<MeteorologicalInfoEntity> getAll(Pageable pagination){
        return repository.findAll(pagination);
    }

    @Transactional
    public MeteorologicalInfoEntity createMeteorologicalInfo(@RequestBody MeteorologicalInfoEntity metInfoEntity){
            return repository.save(metInfoEntity);
    }

    public  MeteorologicalInfoEntity findById(Long id){
        Optional<MeteorologicalInfoEntity> metinfo = repository.findById(id);
        return metinfo.orElse(null);
    }

    @Transactional
    public void deleteMeteorologicalInfoById(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public void  editMeteorologicalInfo(@RequestBody MeteorologicalInfoEntity meteorologicalEntity){
        MeteorologicalInfoEntity metinfo =  repository.getReferenceById(meteorologicalEntity.getId());
        metinfo.atualizarinformacoes(meteorologicalEntity);
    }


    

}

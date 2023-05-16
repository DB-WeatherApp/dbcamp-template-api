package com.template.business.services;

import com.template.Exception.MeteorologicalInfoNotFound;
import com.template.Exception.MissingParameterException;
import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.repository.MeteorologicalInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

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
        return metinfo.orElseThrow(()-> new MeteorologicalInfoNotFound("Informação Meteorológica não encontrada!"));
    }

    @Transactional
    public void deleteMeteorologicalInfoById(Long id){
        findById(id);
        repository.deleteById(id);
    }

    @Transactional
    public MeteorologicalInfoEntity editMeteorologicalInfo(@RequestBody MeteorologicalInfoEntity meteorologicalEntity){
        MeteorologicalInfoEntity metinfo =  repository.getReferenceById(meteorologicalEntity.getId());
        metinfo.atualizarinformacoes(meteorologicalEntity);
        return meteorologicalEntity;
    }

    public List<MeteorologicalInfoEntity> findByCity(String city){
        return repository.findByCity(city);
    }



    

}

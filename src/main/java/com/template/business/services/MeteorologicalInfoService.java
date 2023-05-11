package com.template.business.services;

import com.template.Exception.MissingParameterException;
import com.template.data.DTO.MeteorologicalInfoDTO;
import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.repository.MeteorologicalInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

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
        try {
            return repository.save(metInfoEntity);
        }catch (Exception e){
            throw new MissingParameterException("teste");
        }
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

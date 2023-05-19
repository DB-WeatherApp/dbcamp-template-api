package com.template.services;

import com.template.Exception.MeteorologicalInfoNotFound;
import com.template.Exception.MissingParameterException;
import com.template.business.services.MeteorologicalInfoService;
import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.enums.WeatherTypeEnum;
import com.template.data.repository.MeteorologicalInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("MeteorologicalInfo Service Tests")
class MeteorologicalInfoServiceTests {
    @Mock
    MeteorologicalInfoRepository repository;

    @InjectMocks
    private MeteorologicalInfoService service;

    MeteorologicalInfoEntity meteorologicalInfoEntitySucess = new MeteorologicalInfoEntity(
            10L,
            "Salvador",
            LocalDate.of(2023, 05, 11),
            WeatherTypeEnum.SUNNY, WeatherTypeEnum.STORMY,
            38,
            30,
            80,
            75,
            8
    );
    MeteorologicalInfoEntity meteorologicalInfoEntityEdited = new MeteorologicalInfoEntity(
            10L,
            "São Paulo",
            LocalDate.of(2023, 05, 11),
            WeatherTypeEnum.SUNNY, WeatherTypeEnum.STORMY,
            40,
            20,
            50,
            53,
            1
    );

    MeteorologicalInfoEntity meteorologicalInfoFailed = new MeteorologicalInfoEntity(
            30,
            null,
            LocalDate.of(2023, 05, 11),
            WeatherTypeEnum.SUNNY, WeatherTypeEnum.STORMY,
            38,
            30,
            80,
            75,
            8
    );



    @Test
    @DisplayName("Create Sucess - Should create a MeteorologicalInfo Entity with Sucess ")
    void createdNewMeteorologicalInfo() {
        when(repository.save(any())).thenReturn(meteorologicalInfoEntitySucess);

        MeteorologicalInfoEntity newMeteorologicalInfo = service.createMeteorologicalInfo(meteorologicalInfoEntitySucess);

        assertAll(() -> {
            assertNotNull(newMeteorologicalInfo);
            assertEquals(MeteorologicalInfoEntity.class, newMeteorologicalInfo.getClass());
            assertEquals(meteorologicalInfoEntitySucess.getCity(), newMeteorologicalInfo.getCity());
            assertEquals(meteorologicalInfoEntitySucess.getWeatherDate(), newMeteorologicalInfo.getWeatherDate());
            assertEquals(meteorologicalInfoEntitySucess.getMorningWeather(), newMeteorologicalInfo.getMorningWeather());
            assertEquals(meteorologicalInfoEntitySucess.getNightWeather(), newMeteorologicalInfo.getNightWeather());
            assertEquals(meteorologicalInfoEntitySucess.getMinTemperature(), newMeteorologicalInfo.getMinTemperature());
            assertEquals(meteorologicalInfoEntitySucess.getMaxTemperature(), newMeteorologicalInfo.getMaxTemperature());
            assertEquals(meteorologicalInfoEntitySucess.getHumidity(), newMeteorologicalInfo.getHumidity());
            assertEquals(meteorologicalInfoEntitySucess.getPrecipitaion(), newMeteorologicalInfo.getPrecipitaion());
            assertEquals(meteorologicalInfoEntitySucess.getWindSpeed(), newMeteorologicalInfo.getWindSpeed());
        });

    }

    @Test
    @DisplayName("Create Failed - Should not create a MeteorologicalInfo Entity cause have a null parameter")
    void createFaild() {
        try {
            service.createMeteorologicalInfo(new MeteorologicalInfoEntity());
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("FindById Sucess - When find by Id is called, should return a MeteorologicalInfo Entity")
    void whenFindByIdReturnAMeteorologicalInfo() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(meteorologicalInfoEntitySucess));
        Long meteorologicalInfoId = 10L;
        MeteorologicalInfoEntity response = service.findById(meteorologicalInfoId);
        assertAll(()->{
            assertNotNull(response);
            assertEquals(MeteorologicalInfoEntity.class, response.getClass());
            assertEquals(meteorologicalInfoEntitySucess.getCity(), response.getCity());
            assertEquals(meteorologicalInfoEntitySucess.getWeatherDate(), response.getWeatherDate());
            assertEquals(meteorologicalInfoEntitySucess.getMorningWeather(), response.getMorningWeather());
            assertEquals(meteorologicalInfoEntitySucess.getNightWeather(), response.getNightWeather());
            assertEquals(meteorologicalInfoEntitySucess.getMinTemperature(), response.getMinTemperature());
            assertEquals(meteorologicalInfoEntitySucess.getMaxTemperature(), response.getMaxTemperature());
            assertEquals(meteorologicalInfoEntitySucess.getHumidity(), response.getHumidity());
            assertEquals(meteorologicalInfoEntitySucess.getPrecipitaion(), response.getPrecipitaion());
            assertEquals(meteorologicalInfoEntitySucess.getWindSpeed(), response.getWindSpeed());
        });

    }

    @Test
    @DisplayName("FindById Failed - When find by Id is called with a nonexistent ID, should return 404 not found")
    void whenFindByIdReturnExeption() {
        Long meteorologicalInfoId = 10L;
        try {
            service.findById(meteorologicalInfoId);
        } catch (Exception e) {
            assertEquals(MeteorologicalInfoNotFound.class, e.getClass());
        }
    }

    @Test
    @DisplayName("FindAll Sucess - When FindAll is called should return all MeteorologicalInfos registred in the DB")
    void getAllSucess() {
        Pageable page = mock(Pageable.class);
        List<MeteorologicalInfoEntity> meteorologicalInfoList = List.of(meteorologicalInfoEntitySucess);
        Page<MeteorologicalInfoEntity> pagination = new PageImpl<>(meteorologicalInfoList);
        when(repository.findAll(any(Pageable.class))).thenReturn(pagination);

        Page<MeteorologicalInfoEntity> response = service.getAll(page);

        assertNotNull(response);
        assertEquals(meteorologicalInfoList, response.getContent());
        assertEquals(1, response.getTotalElements());
    }

    @Test
    @DisplayName("Delete By ID Sucesss - Should Delete a MeteorologicalInfo in the DB by its ID")
    void DeleteByIdSucess() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(meteorologicalInfoEntitySucess));
        Long meteorologicalInfoId = 10L;
        doNothing().when(repository).deleteById(meteorologicalInfoId);
        service.deleteMeteorologicalInfoById(meteorologicalInfoId);
        verify(repository, times(1)).deleteById(meteorologicalInfoId);
    }

    @Test
    @DisplayName("DeleteByID Failed- When delete by ID its called with a nonexistent ID, should return a error 404 not found")
    void deleteWithObjectNotFound() {
        Long meteorologicalInfoId = 10L;
        when(repository.findById(anyLong())).thenThrow(new MeteorologicalInfoNotFound("Informação Meteorológica não encontrada!"));
        try {
            service.deleteMeteorologicalInfoById(meteorologicalInfoId);
        } catch (Exception e) {
            assertEquals(MeteorologicalInfoNotFound.class, e.getClass());
        }
    }

    @Test
    @DisplayName("Upadete Sucess - When editMeteorologicalInfo is called should find the entity by its Id and Update with new parameters in the DB")
    void editByIdSucess(){
        when(repository.getReferenceById(meteorologicalInfoEntitySucess.getId())).thenReturn(meteorologicalInfoEntitySucess);
        MeteorologicalInfoEntity editedMeteorologicalInfo = service.editMeteorologicalInfo(meteorologicalInfoEntityEdited);

        assertAll(()->{
            assertNotNull(editedMeteorologicalInfo);
            assertEquals(meteorologicalInfoEntitySucess.getClass(), editedMeteorologicalInfo.getClass());
            assertEquals(meteorologicalInfoEntitySucess.getCity(), editedMeteorologicalInfo.getCity());
            assertEquals(meteorologicalInfoEntitySucess.getWeatherDate(), editedMeteorologicalInfo.getWeatherDate());
            assertEquals(meteorologicalInfoEntitySucess.getMorningWeather(), editedMeteorologicalInfo.getMorningWeather());
            assertEquals(meteorologicalInfoEntitySucess.getNightWeather(), editedMeteorologicalInfo.getNightWeather());
            assertEquals(meteorologicalInfoEntitySucess.getMinTemperature(), editedMeteorologicalInfo.getMinTemperature());
            assertEquals(meteorologicalInfoEntitySucess.getMaxTemperature(), editedMeteorologicalInfo.getMaxTemperature());
            assertEquals(meteorologicalInfoEntitySucess.getHumidity(), editedMeteorologicalInfo.getHumidity());
            assertEquals(meteorologicalInfoEntitySucess.getPrecipitaion(), editedMeteorologicalInfo.getPrecipitaion());
            assertEquals(meteorologicalInfoEntitySucess.getWindSpeed(),editedMeteorologicalInfo.getWindSpeed());
        });
    }
    @Test
    @DisplayName("Upadete Failed - When editMeteorologicalInfo is with null MeteorologicalInfoEntity, should recieve a null pointer exeception")
    void editByIdWithNullMeteorologicalEntity(){
        try{
            service.editMeteorologicalInfo(new MeteorologicalInfoEntity());
        } catch (Exception e){
            assertEquals(NullPointerException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("Upadete Failed - When editMeteorologicalInfo is called with Inexistent ID, should recieve a RunTime Exeception")
    void editByIdWithInexistentId(){
        when(repository.getReferenceById(meteorologicalInfoFailed.getId())).thenThrow(new RuntimeException());
        try{
            service.editMeteorologicalInfo(meteorologicalInfoFailed);
        } catch (Exception e){
            assertEquals(RuntimeException.class, e.getClass());
        }
    }
    @Test
    @DisplayName("Find By City Sucess - When findByCity is called with a city that exist, should return page of MeteorologicalInfo with this city")
    void findByCitySucess(){
        Pageable page = mock(Pageable.class);
        String city = "Salvador";
        List<MeteorologicalInfoEntity> meteorologicalInfoList = List.of(meteorologicalInfoEntitySucess);
        Page<MeteorologicalInfoEntity> pagination = new PageImpl<MeteorologicalInfoEntity>(meteorologicalInfoList);
        when(repository.findByCity(eq(city), any(Pageable.class))).thenReturn(pagination);
        Page<MeteorologicalInfoEntity> metInfoList = service.findByCity(meteorologicalInfoEntitySucess.getCity(),page);

        assertNotNull(metInfoList);
        assertEquals(meteorologicalInfoList, metInfoList.getContent());
        assertEquals(1, metInfoList.getTotalElements());

    }
    @Test
    @DisplayName("Find By City Failed - When findByCity is called with a city that dont exist, should return Runtime Exeception ")
    void findByCityFailed(){
        Pageable page = mock(Pageable.class);
        when(repository.findByCity(eq(meteorologicalInfoEntitySucess.getCity()),any(Pageable.class))).thenThrow(new RuntimeException());
        try{
            service.findByCity(meteorologicalInfoEntitySucess.getCity(),page);
        }catch (Exception e){
            assertEquals(RuntimeException.class, e.getClass());
        }


    }



}
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
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
			LocalDate.of(2023,05,11),
			WeatherTypeEnum.SUNNY,WeatherTypeEnum.STORMY,
			38,
			30,
			80,
			75,
			8
	);

	MeteorologicalInfoEntity meteorologicalInfoFailed  = new MeteorologicalInfoEntity(
			10L,
			null,
			LocalDate.of(2023,05,11),
			WeatherTypeEnum.SUNNY,WeatherTypeEnum.STORMY,
			38,
			30,
			80,
			75,
			8
	);


	@Test
	@DisplayName("Create - POST Sucess")
	void createdNewMeteorologicalInfo() {
		when(repository.save(any())).thenReturn(meteorologicalInfoEntitySucess);

		MeteorologicalInfoEntity newMeteorologicalInfo = service.createMeteorologicalInfo(meteorologicalInfoEntitySucess);

		assertNotNull(newMeteorologicalInfo);
		assertEquals(MeteorologicalInfoEntity.class, newMeteorologicalInfo.getClass());
		assertEquals(meteorologicalInfoEntitySucess.getCity(),newMeteorologicalInfo.getCity());
		assertEquals(meteorologicalInfoEntitySucess.getWeatherDate(),newMeteorologicalInfo.getWeatherDate());
		assertEquals(meteorologicalInfoEntitySucess.getMorningWeather(),newMeteorologicalInfo.getMorningWeather());
		assertEquals(meteorologicalInfoEntitySucess.getNightWeather(),newMeteorologicalInfo.getNightWeather());
		assertEquals(meteorologicalInfoEntitySucess.getMinTemperature(),newMeteorologicalInfo.getMinTemperature());
		assertEquals(meteorologicalInfoEntitySucess.getMaxTemperature(),newMeteorologicalInfo.getMaxTemperature());
		assertEquals(meteorologicalInfoEntitySucess.getHumidity(),newMeteorologicalInfo.getHumidity());
		assertEquals(meteorologicalInfoEntitySucess.getPrecipitaion(),newMeteorologicalInfo.getPrecipitaion());
		assertEquals(meteorologicalInfoEntitySucess.getWindSpeed(),newMeteorologicalInfo.getWindSpeed());
	}

	@Test
	@DisplayName("Create - POST Failed")
	void createFaild(){
		try{
			service.createMeteorologicalInfo(new MeteorologicalInfoEntity());
		} catch (Exception e){
			assertEquals(RuntimeException.class, e.getClass());
		}
	}

	@Test
	@DisplayName("Find By Id - Get Sucess")
	void whenFindByIdReturnAMeteorologicalInfo(){
		when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(meteorologicalInfoEntitySucess));
		Long meteorologicalInfoId = 10L;
		MeteorologicalInfoEntity response = service.findById(meteorologicalInfoId);

		assertNotNull(response);
		assertEquals(MeteorologicalInfoEntity.class, response.getClass());
		assertEquals(meteorologicalInfoEntitySucess.getCity(),response.getCity());
		assertEquals(meteorologicalInfoEntitySucess.getWeatherDate(),response.getWeatherDate());
		assertEquals(meteorologicalInfoEntitySucess.getMorningWeather(),response.getMorningWeather());
		assertEquals(meteorologicalInfoEntitySucess.getNightWeather(),response.getNightWeather());
		assertEquals(meteorologicalInfoEntitySucess.getMinTemperature(),response.getMinTemperature());
		assertEquals(meteorologicalInfoEntitySucess.getMaxTemperature(),response.getMaxTemperature());
		assertEquals(meteorologicalInfoEntitySucess.getHumidity(),response.getHumidity());
		assertEquals(meteorologicalInfoEntitySucess.getPrecipitaion(),response.getPrecipitaion());
		assertEquals(meteorologicalInfoEntitySucess.getWindSpeed(),response.getWindSpeed());
	}
	@Test
	@DisplayName("Find By Id - Get Failed")
	void whenFindByIdReturnExeption(){
		Long meteorologicalInfoId = 10L;
		try{
			service.findById(meteorologicalInfoId);
		} catch (Exception e){
			assertEquals(MeteorologicalInfoNotFound.class, e.getClass());
		}
	}

	@Test
	@DisplayName("GetAll - GET Success")
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
	@DisplayName("DeleteByID - DELETE Sucess")
	void DeleteByIdSucess(){
		when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(meteorologicalInfoEntitySucess));
		Long meteorologicalInfoId = 10L;
		doNothing().when(repository).deleteById(meteorologicalInfoId);
		service.deleteMeteorologicalInfoById(meteorologicalInfoId);
		verify(repository,times(1)).deleteById(meteorologicalInfoId);
	}

	@Test
	@DisplayName("DeleteByID- Delete Failed")
	void deleteWithObjectNotFound(){
		Long meteorologicalInfoId = 10L;
		when(repository.findById(anyLong())).thenThrow(new MeteorologicalInfoNotFound("Informação Meteorológica não encontrada!"));
		try{
			service.deleteMeteorologicalInfoById(meteorologicalInfoId);
		} catch(Exception e){
			assertEquals(MeteorologicalInfoNotFound.class, e.getClass());
		}
	}

}
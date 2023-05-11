package com.template.services;

import com.template.Exception.MissingParameterException;
import com.template.business.services.MeteorologicalInfoService;
import com.template.data.DTO.MeteorologicalInfoDTO;
import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.enums.WeatherTypeEnum;
import com.template.data.repository.MeteorologicalInfoRepository;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
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
			"Salvador",
			LocalDate.of(2023,05,11),
			WeatherTypeEnum.SUNNY,WeatherTypeEnum.STORMY,
			38,
			30,
			80,
			75,
			8
	);
	MeteorologicalInfoDTO meteorologicalInfoEntitySucessDTO = new MeteorologicalInfoDTO(
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
			null,
			LocalDate.of(2023,05,11),
			WeatherTypeEnum.SUNNY,WeatherTypeEnum.STORMY,
			38,
			30,
			80,
			75,
			8
	);

	MeteorologicalInfoDTO meteorologicalInfoFailedDTO  = new MeteorologicalInfoDTO(
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
		assertEquals(meteorologicalInfoEntitySucessDTO.weatherDate(),newMeteorologicalInfo.getWeatherDate());
		assertEquals(meteorologicalInfoEntitySucessDTO.morningWeather(),newMeteorologicalInfo.getMorningWeather());
		assertEquals(meteorologicalInfoEntitySucessDTO.nightWeather(),newMeteorologicalInfo.getNightWeather());
		assertEquals(meteorologicalInfoEntitySucessDTO.minTemperature(),newMeteorologicalInfo.getMinTemperature());
		assertEquals(meteorologicalInfoEntitySucessDTO.maxTemperature(),newMeteorologicalInfo.getMaxTemperature());
		assertEquals(meteorologicalInfoEntitySucessDTO.humidity(),newMeteorologicalInfo.getHumidity());
		assertEquals(meteorologicalInfoEntitySucessDTO.precipitaion(),newMeteorologicalInfo.getPrecipitaion());
		assertEquals(meteorologicalInfoEntitySucessDTO.windSpeed(),newMeteorologicalInfo.getWindSpeed());
	}

	@Test
	@DisplayName("Create - POST Failed")
	void createFaild(){
		var entityNull = new MeteorologicalInfoEntity();

			assertThrows(MissingParameterException.class,()-> service.createMeteorologicalInfo(entityNull));
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
		Long meteorologicalInfoId = 10L;
		doNothing().when(repository).deleteById(meteorologicalInfoId);
		service.deleteMeteorologicalInfoById(meteorologicalInfoId);
		verify(repository,times(1)).deleteById(meteorologicalInfoId);
	}

	/*@Test
	@DisplayName("DeleteByID- Delete Failed")
	void deleteWithObjectNotFound(){
		Long meteorologicalInfoId = 10L;
		 when(repository.findById(anyLong()))
				 .thenThrow(new ObjectNotFoundException());
		try{
			service.deleteMeteorologicalInfoById(meteorologicalInfoId);
		} catch(Exception e){
			assertEquals(ObjectNotFoundException.class, e.getClass());
		}


	}

*/

}
package com.template.services;

import com.template.business.services.MeteorologicalInfoService;
import com.template.data.DTO.MeteorologicalInfoDTO;
import com.template.data.entity.MeteorologicalInfoEntity;
import com.template.data.enums.WeatherTypeEnum;
import com.template.data.repository.MeteorologicalInfoRepository;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
		MeteorologicalInfoEntity newMeteorologicalInfo = service.create(meteorologicalInfoEntitySucessDTO);
		assertNotNull(newMeteorologicalInfo);
		verify(repository).save(any());
	}

	@Test
	@DisplayName("Create - POST Failed")
	void createFaild(){
		when(repository.save(any())).thenReturn(meteorologicalInfoFailed);
		MeteorologicalInfoEntity newMeteorologicalInfo = service.create(meteorologicalInfoFailedDTO);
		assertEquals(null,meteorologicalInfoFailed.getCity());
		verify(repository).save(any());
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



}
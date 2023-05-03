package com.template.services;

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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
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
	@Test
	@DisplayName("Create - Post Sucess")
	void createdNewMeteorologicalInfo() {
		when(repository.save(any())).thenReturn(meteorologicalInfoEntitySucess);
		MeteorologicalInfoEntity newMeteorologicalInfo = service.create(meteorologicalInfoEntitySucess);
		assertNotNull(newMeteorologicalInfo);
		verify(repository).save(any());
	}

	@Test
	@DisplayName("Create - Post Failed")
	void createFaild(){
		when(repository.save(any())).thenReturn(meteorologicalInfoFailed);
		MeteorologicalInfoEntity newMeteorologicalInfo = service.create(meteorologicalInfoFailed);
		assertEquals(null,meteorologicalInfoFailed.getCity());
		verify(repository).save(any());
	}


}
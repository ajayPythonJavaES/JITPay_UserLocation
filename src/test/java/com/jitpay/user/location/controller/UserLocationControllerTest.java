/**
 * 
 */
package com.jitpay.user.location.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitpay.user.location.entity.Location;
import com.jitpay.user.location.entity.UserLocation;
import com.jitpay.user.location.repository.UserLocationRepository;

/**
 * @author Ajay Sarvasiddhi
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserLocationControllerTest {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private UserLocationRepository mockRepository;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void save_userLocation_OK() throws Exception {
		Location location = new Location(1, 23.5857373, 99.636366);
		UserLocation userLocation = new UserLocation(1, "AAA",
				LocalDateTime.parse("2022-10-08T19:00:05.992", DateTimeFormatter.ISO_DATE_TIME), location);

		when(mockRepository.save(any(UserLocation.class))).thenReturn(userLocation);

		String expected = objectMapper.writeValueAsString(userLocation);

		ResponseEntity<String> response = testRestTemplate.postForEntity("/user/location", expected, String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);

		verify(mockRepository, times(1)).save(any(UserLocation.class));

	}

}

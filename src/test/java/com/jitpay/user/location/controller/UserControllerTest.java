/**
 * 
 */
package com.jitpay.user.location.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitpay.user.location.entity.User;
import com.jitpay.user.location.repository.UserRepository;

/**
 * @author Ajay Sarvasiddhi
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private UserRepository mockRepository;
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void init() {
		User user = new User("AAA", "Alex", "Schmid", "alex.schmid.test@gmail.com", "2022-10-08T11:44:00.524");
		when(mockRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
	}

	@Test
	public void save_user_OK() throws Exception {
		User user = new User("AAA", "Alex", "Schmid", "alex.schmid.test@gmail.com", "2022-10-08T11:44:00.524");
		when(mockRepository.save(any(User.class))).thenReturn(user);
		
		String expected = objectMapper.writeValueAsString(user);
		
		ResponseEntity<String> response = testRestTemplate.postForEntity("/user", expected, String.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
		verify(mockRepository, times(1)).save(any(User.class));
		
	}
	
	@Test
	public void save_user_NOT_OK() throws Exception {
		User user = new User("AAA", "Alex", "Schmid", "alex.schmid.test@gmail.com", "2022-10-08T11:44:00.524");
		when(mockRepository.save(any(User.class))).thenReturn(user);
		
		String expected = objectMapper.writeValueAsString(user);
		
		ResponseEntity<String> response = testRestTemplate.postForEntity("/user", expected, String.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
		verify(mockRepository, times(1)).save(any(User.class));
		
	}
	
	@Test
	public void find_userId_OK() throws Exception {
		String expected = "{userId: AAA, firstName:\"Alex\", secondName:\"Schmid\", email:\"alex.schmid.test@gmail.com\", createdOn:\"2022-10-08T11:44:00.524\"}";
		ResponseEntity<Object> response = testRestTemplate.getForEntity("/user/AAA", Object.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
		
		JSONAssert.assertEquals(expected, new JSONObject(response.getBody().toString()), false);
		verify(mockRepository, times(1)).findById("AAA");
		
	}

	/*
	 * private static void printJSON(Object object) { String result; try { result =
	 * objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	 * System.out.println(result); } catch (JsonProcessingException e) {
	 * e.printStackTrace(); } }
	 */

}

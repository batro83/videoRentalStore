package com.singularcover.videoRentalStore.integration;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:data-test.sql" })
public class RentControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void rentFilmsTest() {
		final Long idCustomer = 2L;
		final String filmsToRent = asList(1L, 2L).stream().map(Object::toString).collect(joining(","));

		final Integer days = 10;
		final String path = "/api/{customer}/rent/{films}/{days}";

		final ResponseEntity<String> response = restTemplate.exchange(path, POST, EMPTY, String.class, idCustomer,
				filmsToRent, days);

		assertEquals(OK, response.getStatusCode());
		assertEquals("{\"price\":38,\"points\":3}", response.getBody());
	}

	@Test
	public void rentFilmsTest2() {
		final Long idCustomer = 2L;
		final String filmsToRent = asList(1L, 2L).stream().map(Object::toString).collect(joining(","));
		;
		final Integer days = 10;
		final String path = "/api/{customer}/rent/{films}/{days}";

		final ResponseEntity<String> response = restTemplate.exchange(path, POST, EMPTY, String.class, idCustomer,
				filmsToRent, days);

		assertEquals(OK, response.getStatusCode());
		assertEquals("{\"price\":38,\"points\":3}", response.getBody());
	}

	@Test
	public void returnFilmsTest() {
		final Long idCustomer = 1L;
		final String filmsToRent = asList(4L, 3L).stream().map(Object::toString).collect(joining(","));
		;
		final String path = "/api/{customer}/return/{films}";

		final ResponseEntity<String> response = restTemplate.exchange(path, POST, EMPTY, String.class, idCustomer,
				filmsToRent);

		assertEquals(OK, response.getStatusCode());
		assertEquals("{\"surcharges\":8}", response.getBody());

		// 1 New release 1 OlFilm for 2 days 4 days ago. Days late 2.
		// 2 days late * 3e(premium) + 2 days late * 1e(basic) = 8 euros
	}
}
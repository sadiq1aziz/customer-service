package com.app.customer;

import com.app.customer.client.MovieRestClient;
import com.app.customer.domain.Genre;
import com.app.customer.dto.CustomerDto;
import com.app.customer.dto.MovieDto;
import com.app.customer.dto.UpdateGenreRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.AutoConfigureRestClient;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Import(TestcontainersConfiguration.class)
@MockitoBean(types = {RestClient.class, MovieRestClient.class})
@AutoConfigureRestTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerApplicationTests {

	@Autowired
	private RestTestClient client;

	@Autowired
	private MovieRestClient movieClient;

	@Test
	void contextLoads() {
	}

	@Test
	public void healthCheck(){
				client
				.get()
				.uri("/actuator/health")
				.exchange()
				.expectStatus()
				.is2xxSuccessful();
	}

	// use mock	beans to simulate behavior of fetching movies using test client
	@Test
	public void testFetchCustomerById(){
		List<MovieDto> list = new ArrayList<>();
		list.add(new MovieDto(1, "Rambo", 1995, Genre.ACTION));
		list.add(new MovieDto(2, "Batman", 1998, Genre.ACTION));
		Mockito.when(movieClient.callMoviesByGenre(Genre.ACTION)).thenReturn(list);

		var res = client
				.get()
				.uri("/api/customers/customer/1")
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.returnResult(CustomerDto.class)
				.getResponseBody();

		Assertions.assertNotNull(res);
		Assertions.assertEquals(Genre.ACTION, res.favoriteGenre());
		Assertions.assertEquals("Rambo", res.movieRecommendations().get(0).title());
	}

	@Test
	public void testCustomerNotFound(){
		client
				.get()
				.uri("/api/customers/customer/99999")
				.exchange()
				.expectStatus()
				.is4xxClientError();
	}

	@Test
	public void testUpdateCustomerGenre(){
		UpdateGenreRequest req = new UpdateGenreRequest(Genre.COMEDY);
		client
				.patch()
				.uri("/api/customers/99999/genre")
				.body(req)
				.exchange()
				.expectStatus()
				.is4xxClientError();
	}




}

package com.app.customer.client;

import com.app.customer.domain.Genre;
import com.app.customer.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MovieRestClient {

    private static final Logger log = LoggerFactory.getLogger(MovieRestClient.class);
    private final RestClient client;
    public MovieRestClient(RestClient client){
        this.client = client;
    }

    public List<MovieDto> callMoviesByGenre(Genre genre){
        log.info("Invoking movies by {} genre", genre);
        var movieList = client
                .get()
                .uri("/api/movies/{genre}", genre)
                .retrieve()
                .body(new ParameterizedTypeReference<List<MovieDto>>(){});
        log.info("movieList: ", movieList);
        return movieList;
    }
}

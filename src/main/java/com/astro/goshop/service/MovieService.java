package com.astro.goshop.service;

import com.astro.goshop.model.Movie;
import com.astro.goshop.model.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    private String apiEndpoint;

    private RestTemplate restTemplate;

    @Autowired
    public MovieService(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.apiEndpoint = env.getRequiredProperty("api.endpoint");
    }

    /**
     * get all the movies' title that are matched the provided value
     *
     * @param value value used for matching the movie
     * @return list of movie titles that match the provided value
     */
    public String[] getMovieTitles(String value) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiEndpoint);
        if (value != null && value.trim().length() > 0) {
            uriBuilder.queryParam("Title", value.trim());
        }
        int movieIndex = 0;
        int totalPage = 1;
        int currentPage = 0;
        String[] movieTitles = new String[0];
        MovieResponseType responseType = new MovieResponseType();
        do {
            URI uri = uriBuilder.replaceQueryParam("page", ++currentPage).build().toUri();
            ResponseEntity<Pageable<Movie>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
            Pageable<Movie> response = responseEntity.getBody();
            if (totalPage == 1) {
                totalPage = response.getTotalPages();
                int totalMovieCount = response.getTotal();
                movieTitles = new String[totalMovieCount];
            }
            List<Movie> movies = response.getData();
            for (Movie movie : movies) {
                movieTitles[movieIndex++] = movie.getTitle();
            }
        } while (currentPage < totalPage);
        Arrays.sort(movieTitles);
        return movieTitles;
    }
}

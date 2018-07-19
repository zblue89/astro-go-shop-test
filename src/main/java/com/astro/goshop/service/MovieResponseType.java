package com.astro.goshop.service;

import com.astro.goshop.model.Movie;
import com.astro.goshop.model.Pageable;
import org.springframework.core.ParameterizedTypeReference;

public class MovieResponseType extends ParameterizedTypeReference<Pageable<Movie>> {
}

package com.app.customer.dto;

import com.app.customer.domain.Genre;

import java.util.List;

public record CustomerDto(Integer id,
                          String name,
                          Genre favoriteGenre,
                          List<MovieDto> movieRecommendations){};



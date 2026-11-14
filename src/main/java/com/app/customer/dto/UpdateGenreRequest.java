package com.app.customer.dto;

import com.app.customer.domain.Genre;

public record UpdateGenreRequest(Genre favoriteGenre) {
}

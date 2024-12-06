package pl.fmizielinski.riverside.model

import pl.fmizielinski.riverside.domain.model.Movie

data class MovieUiModel(
    val id: String,
    val title: String,
    val year: String,
    val posterUrl: String
)

fun Movie.toMovieUiModel() = MovieUiModel(
    id = id,
    title = title,
    year = year,
    posterUrl = posterUrl
)

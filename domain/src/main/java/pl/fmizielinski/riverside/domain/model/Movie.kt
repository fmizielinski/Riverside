package pl.fmizielinski.riverside.domain.model

import pl.fmizielinski.riverside.data.model.MovieModel

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val posterUrl: String
)

fun MovieModel.toMovie() = Movie(
    id = id,
    title = title,
    year = year,
    posterUrl = posterUrl
)

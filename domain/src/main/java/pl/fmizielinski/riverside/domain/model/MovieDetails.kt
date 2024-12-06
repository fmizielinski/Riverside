package pl.fmizielinski.riverside.domain.model

import pl.fmizielinski.riverside.data.model.MovieDetailsModel
import pl.fmizielinski.riverside.data.model.RatingModel

data class MovieDetails(
    val id: String,
    val title: String,
    val year: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val country: String,
    val posterUrl: String,
    val ratings: List<Rating>,
)

fun MovieDetailsModel.toMovieDetails() = MovieDetails(
    id = id,
    title = title,
    year = year,
    released = released,
    runtime = runtime,
    genre = genre,
    director = director,
    actors = actors,
    plot = plot,
    country = country,
    posterUrl = posterUrl,
    ratings = ratings.map(RatingModel::toRating),
)

package pl.fmizielinski.riverside.model

import pl.fmizielinski.riverside.domain.model.Movie
import pl.fmizielinski.riverside.domain.model.MovieDetails
import pl.fmizielinski.riverside.domain.model.Rating

sealed interface MovieDetailsUiModel {
    val title: String
    val year: String
    val posterUrl: String
    val ratings: List<RatingUiModel>

    data class Loading(
        override val title: String,
        override val year: String,
        override val posterUrl: String,
    ) : MovieDetailsUiModel {
        override val ratings: List<RatingUiModel> = emptyList()
    }

    data class Loaded(
        override val title: String,
        override val year: String,
        val released: String,
        val runtime: String,
        val genre: String,
        val director: String,
        val actors: String,
        val plot: String,
        val country: String,
        override val posterUrl: String,
        override val ratings: List<RatingUiModel>,
    ) : MovieDetailsUiModel
}

fun MovieDetails.toMovieDetailsUiModel() = MovieDetailsUiModel.Loaded(
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
    ratings = ratings.map(Rating::toMovieUiModel),
)

fun Movie.toMovieDetailsUiModel() = MovieDetailsUiModel.Loading(
    title = title,
    year = year,
    posterUrl = posterUrl,
)

package pl.fmizielinski.riverside.domain.model

import pl.fmizielinski.riverside.data.model.RatingModel

sealed interface Rating {
    val value: String

    data class Imdb(override val value: String) : Rating
    data class RottenTomatoes(override val value: String) : Rating
    data class Metacritic(override val value: String) : Rating
}

fun RatingModel.toRating(): Rating = when (source) {
    RatingModel.Source.IMDB -> Rating.Imdb(value)
    RatingModel.Source.ROTTEN_TOMATOES -> Rating.RottenTomatoes(value)
    RatingModel.Source.METACRITIC -> Rating.Metacritic(value)
}

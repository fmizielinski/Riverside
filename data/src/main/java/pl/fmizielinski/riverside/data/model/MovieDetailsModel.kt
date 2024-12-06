package pl.fmizielinski.riverside.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsModel(
    @SerialName("imdbID") val id: String,
    @SerialName("Title") val title: String,
    @SerialName("Year") val year: String,
    @SerialName("Released") val released: String,
    @SerialName("Runtime") val runtime: String,
    @SerialName("Genre") val genre: String,
    @SerialName("Director") val director: String,
    @SerialName("Actors") val actors: String,
    @SerialName("Plot") val plot: String,
    @SerialName("Country") val country: String,
    @SerialName("Poster") val posterUrl: String,
    @SerialName("Ratings") val ratings: List<RatingModel>,
)

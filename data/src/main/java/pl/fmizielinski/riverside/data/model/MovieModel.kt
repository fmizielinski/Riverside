package pl.fmizielinski.riverside.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    @SerialName("imdbID") val id: String,
    @SerialName("Title") val title: String,
    @SerialName("Year") val year: String,
    @SerialName("Poster") val posterUrl: String,
)

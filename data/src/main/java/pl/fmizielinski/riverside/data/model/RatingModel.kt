package pl.fmizielinski.riverside.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingModel(
    @SerialName("Source") val source: Source,
    @SerialName("Value") val value: String
) {

    @Serializable
    enum class Source {
        @SerialName("Internet Movie Database")
        IMDB,

        @SerialName("Rotten Tomatoes")
        ROTTEN_TOMATOES,

        @SerialName("Metacritic")
        METACRITIC
    }
}

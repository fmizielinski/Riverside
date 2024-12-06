package pl.fmizielinski.riverside.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("Search") val search: List<MovieModel>,
)

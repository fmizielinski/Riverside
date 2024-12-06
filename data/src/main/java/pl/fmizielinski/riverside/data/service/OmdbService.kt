package pl.fmizielinski.riverside.data.service

import pl.fmizielinski.riverside.data.model.MovieDetailsModel
import pl.fmizielinski.riverside.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {

    @GET("/")
    suspend fun searchMovies(@Query("s") query: String): SearchResponse // TODO paging

    @GET("/")
    suspend fun getMovieDetails(@Query("i") id: String): MovieDetailsModel
}

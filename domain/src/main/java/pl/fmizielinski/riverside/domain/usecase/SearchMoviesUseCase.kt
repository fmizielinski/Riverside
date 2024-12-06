package pl.fmizielinski.riverside.domain.usecase

import org.koin.core.annotation.Factory
import pl.fmizielinski.riverside.data.model.MovieModel
import pl.fmizielinski.riverside.data.service.OmdbService
import pl.fmizielinski.riverside.domain.model.Movie
import pl.fmizielinski.riverside.domain.model.toMovie

@Factory
class SearchMoviesUseCase(private val omdbService: OmdbService) {

    suspend operator fun invoke(query: String): List<Movie> {
        return omdbService.searchMovies(query.trim())
            .search
            .map(MovieModel::toMovie)
    }
}

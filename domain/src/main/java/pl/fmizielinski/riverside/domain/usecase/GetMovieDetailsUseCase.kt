package pl.fmizielinski.riverside.domain.usecase

import org.koin.core.annotation.Factory
import pl.fmizielinski.riverside.data.service.OmdbService
import pl.fmizielinski.riverside.domain.model.MovieDetails
import pl.fmizielinski.riverside.domain.model.toMovieDetails

@Factory
class GetMovieDetailsUseCase(
    private val omdbService: OmdbService,
) {

    suspend operator fun invoke(id: String): MovieDetails = omdbService.getMovieDetails(id)
        .toMovieDetails()
}

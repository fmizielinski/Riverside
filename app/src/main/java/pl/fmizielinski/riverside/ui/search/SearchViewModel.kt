package pl.fmizielinski.riverside.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import pl.fmizielinski.riverside.R
import pl.fmizielinski.riverside.domain.model.Movie
import pl.fmizielinski.riverside.domain.repository.EventsRepository
import pl.fmizielinski.riverside.domain.repository.EventsRepository.Event
import pl.fmizielinski.riverside.domain.usecase.SearchMoviesUseCase
import pl.fmizielinski.riverside.model.MovieUiModel
import pl.fmizielinski.riverside.model.toMovieUiModel
import pl.fmizielinski.riverside.utils.SingleLiveEvent

@KoinViewModel
class SearchViewModel(
    private val eventsRepository: EventsRepository,
    private val searchMoviesUseCase: SearchMoviesUseCase,
) : ViewModel() {

    private val _results = MutableLiveData<List<Movie>>()
    val results: LiveData<List<MovieUiModel>> = _results
        .map {
            it.map(Movie::toMovieUiModel)
        }

    private val _openDetails = SingleLiveEvent<Unit>()
    val openDetails: LiveData<Unit> = _openDetails

    fun searchMovies(query: String) {
        if (query.isBlank()) return
        viewModelScope.launch {
            try {
                val result = searchMoviesUseCase(query)
                _results.postValue(result)
            } catch (e: Exception) {
                eventsRepository.postEvent(Event.Error(R.string.main_searchError))
            }
        }
    }

    fun itemClicked(movie: MovieUiModel) {
        viewModelScope.launch {
            _results.value?.firstOrNull { it.id == movie.id }?.let {
                _openDetails.postValue(Unit)
                eventsRepository.postEvent(Event.LoadMovieDetails(it))
            }
        }
    }
}

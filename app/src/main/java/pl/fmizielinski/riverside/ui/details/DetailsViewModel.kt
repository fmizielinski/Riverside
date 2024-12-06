package pl.fmizielinski.riverside.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import pl.fmizielinski.riverside.R
import pl.fmizielinski.riverside.domain.model.Movie
import pl.fmizielinski.riverside.domain.repository.EventsRepository
import pl.fmizielinski.riverside.domain.repository.EventsRepository.Event
import pl.fmizielinski.riverside.domain.usecase.GetMovieDetailsUseCase
import pl.fmizielinski.riverside.model.MovieDetailsUiModel
import pl.fmizielinski.riverside.model.toMovieDetailsUiModel

@KoinViewModel
class DetailsViewModel(
    private val eventsRepository: EventsRepository,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {

    private val _details = MutableLiveData<MovieDetailsUiModel>()
    val details: LiveData<MovieDetailsUiModel> = _details

    init {
        viewModelScope.launch {
            eventsRepository.events
                .filterIsInstance<Event.LoadMovieDetails>()
                .map { it.movie }
                .collect(::loadDetails)
        }
    }

    fun loadDetails(movie: Movie) {
        val loadingDetails = movie.toMovieDetailsUiModel()
        _details.postValue(loadingDetails)
        viewModelScope.launch {
            try {
                val loadedDetails = getMovieDetailsUseCase(movie.id).toMovieDetailsUiModel()
                _details.postValue(loadedDetails)
            } catch (e: Exception) {
                eventsRepository.postEvent(Event.Error(R.string.main_detailsError))
            }
        }
    }
}

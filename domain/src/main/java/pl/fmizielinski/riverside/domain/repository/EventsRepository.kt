package pl.fmizielinski.riverside.domain.repository

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.annotation.Single
import pl.fmizielinski.riverside.domain.model.Movie

@Single
class EventsRepository {

    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> = _events

    suspend fun postEvent(event: Event) {
        _events.emit(event)
    }

    sealed interface Event {
        data class LoadMovieDetails(val movie: Movie) : Event
        data class Error(val messageId: Int) : Event
    }
}

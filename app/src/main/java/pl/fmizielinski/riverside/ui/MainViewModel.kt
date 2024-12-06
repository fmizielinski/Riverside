package pl.fmizielinski.riverside.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import pl.fmizielinski.riverside.domain.repository.EventsRepository
import pl.fmizielinski.riverside.domain.repository.EventsRepository.Event
import pl.fmizielinski.riverside.utils.SingleLiveEvent

@KoinViewModel
class MainViewModel(
    eventsRepository: EventsRepository,
) : ViewModel() {

    private val _errors = SingleLiveEvent<Int>()
    val errors: LiveData<Int> = _errors

    init {
        viewModelScope.launch {
            eventsRepository.events
                .filterIsInstance<Event.Error>()
                .collect {
                    _errors.postValue(it.messageId)
                }
        }
    }
}

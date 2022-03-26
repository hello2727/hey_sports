package com.example.android.heysports.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.heysports.domain.net.YoutubeSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Jihye Noh
 * Date: 2022-02-05
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRepo: YoutubeSearchRepository
) : ViewModel() {
    private val scope = viewModelScope + CoroutineExceptionHandler { _, e ->
        Timber.e(e.toString())
    }

    init {
        scope.launch {
            getIntroVideoId(VIDEO_KEYWORD)
        }
    }

    fun onOlympicClick(){

    }

    fun onWorldCupClick(){

    }

    private suspend fun getIntroVideoId(keyword: String) {
        event(
            Event.SetVideoIdEvent(
                searchRepo.getSearchVideoList(keyword)?.items?.get(0)?.id?.videoId
                    ?: DEFAULT_VIDEO_ID
            )
        )
    }

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow: SharedFlow<Event> = _eventFlow

    private fun event(event: Event) {
        scope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SetVideoIdEvent(val keyword: String) : Event()
    }

    companion object {
        private const val VIDEO_KEYWORD = "olympic highlights"
        private const val DEFAULT_VIDEO_ID = "p6E9R9qv1No"
    }
}
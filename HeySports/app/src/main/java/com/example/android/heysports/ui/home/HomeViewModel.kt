package com.example.android.heysports.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.heysports.network.model.SearchVo
import com.example.android.heysports.network.repo.YoutubeSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
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

    // TODO: flatMapLatest {  } 선언하기
    private val _introVideoId = MutableStateFlow("p6E9R9qv1No")
    val introVideoId: StateFlow<String> = _introVideoId

    val introductionVideo: Flow<SearchVo?> = flow {
        emit(searchRepo.getSearchVideoList())
    }
}
package com.example.android.heysports.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.heysports.network.model.SearchVo
import com.example.android.heysports.network.repo.YoutubeSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    val introductionVideo: Flow<SearchVo?> = flow {
        emit(searchRepo.getSearchVideoList())
    }
}
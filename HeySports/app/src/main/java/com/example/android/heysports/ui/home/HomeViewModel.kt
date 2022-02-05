package com.example.android.heysports.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Jihye Noh
 * Date: 2022-02-05
 */
@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val scope = viewModelScope + CoroutineExceptionHandler { _, e ->
        Timber.e(e.toString())
    }
}
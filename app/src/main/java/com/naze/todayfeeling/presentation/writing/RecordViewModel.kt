package com.naze.todayfeeling.presentation.writing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
) : ViewModel() {
    private val _selectState = MutableStateFlow(false)
    val selectState = _selectState.asStateFlow()

    private val _recordState = MutableStateFlow(false)
    val recordState = _recordState.asStateFlow()

    fun changeSelectStateFlow() {
        viewModelScope.launch {
            _selectState.value = true
            Log.d("Fragment Test","selectState : ${selectState.value}")
        }
    }

    fun changeRecordStateFlow() {
        viewModelScope.launch {
            _recordState.value = true
            Log.d("Fragment Test","recordState : ${recordState.value}")
        }
    }
}
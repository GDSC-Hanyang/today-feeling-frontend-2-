package com.naze.todayfeeling.presentation.clinic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naze.todayfeeling.domain.model.ClinicData
import com.naze.todayfeeling.domain.repository.ClinicRepository
import java.util.*
import kotlin.collections.ArrayList

class ClinicViewModel(private val clinicRepo: ClinicRepository): ViewModel() {
    private val _clinic = MutableLiveData<ClinicData>()
    val clinic: LiveData<ClinicData> get() = _clinic

}
package com.example.gramoday.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramoday.data.models.NetworkResponse
import com.example.gramoday.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val Tag = "MainViewModel"

    private val _res = MutableLiveData<NetworkResponse>()

    val res: LiveData<NetworkResponse>
        get() = _res

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        _res.value = mainRepository.getNetworkResponse()
        Log.i(Tag, _res.value?.name.toString())
    }

}
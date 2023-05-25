package com.example.superhuman.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superhuman.data.HealthDataRepository
import com.example.superhuman.model.HealthModel
import com.example.superhuman.model.RequestBody
import com.example.superhuman.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(private val healthDataRepository:HealthDataRepository):ViewModel()
{
    private val _healthLiveData=MutableLiveData<Resource<HealthModel?>>(Resource.Loading())
    val healthLiveData:LiveData<Resource<HealthModel?>>
        get()=_healthLiveData

    fun getHealthData(requestBody:RequestBody)
    {
        viewModelScope.launch (Dispatchers.IO){
            _healthLiveData.postValue(healthDataRepository.getHealthData(requestBody))
        }
    }
}
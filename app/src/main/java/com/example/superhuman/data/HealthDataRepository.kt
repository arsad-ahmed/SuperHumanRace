package com.example.superhuman.data

import android.util.Log
import com.example.superhuman.model.HealthModel
import com.example.superhuman.model.RequestBody
import com.example.superhuman.util.Resource
import javax.inject.Inject

class HealthDataRepository @Inject constructor(private val healthApi:HealthApi)
{
    suspend fun getHealthData(requestBody:RequestBody):Resource<HealthModel?>
    {
        return try
        {
            val result=healthApi.getHealthData(requestBody)
            Resource.Success(result.body())
        }
        catch(e:Exception)
        {
            Resource.Error(e.message.toString())
        }
    }
}
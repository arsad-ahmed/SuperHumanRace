package com.example.superhuman.data

import com.example.superhuman.model.HealthModel
import com.example.superhuman.model.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HealthApi
{
    @POST("liveFeedApis")
    suspend fun getHealthData(@Body requestBody:RequestBody):Response<HealthModel>
}
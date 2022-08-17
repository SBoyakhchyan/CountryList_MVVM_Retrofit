package com.example.countrylist_mvvm_retrofit.api

import com.example.countrylist_mvvm_retrofit.model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("v2/")
    fun getCountryList(): Call<List<CountryModel>>
}
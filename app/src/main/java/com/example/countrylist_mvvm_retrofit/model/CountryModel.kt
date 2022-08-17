package com.example.countrylist_mvvm_retrofit.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    val name: String?,
    val flags: String?,
    val alpha2Code: String?,
    val capital: String?,
    val region: String?
)
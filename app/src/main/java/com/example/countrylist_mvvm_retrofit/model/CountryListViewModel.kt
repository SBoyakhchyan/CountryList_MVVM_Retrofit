package com.example.countrylist_mvvm_retrofit.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrylist_mvvm_retrofit.api.ApiClient
import com.example.countrylist_mvvm_retrofit.api.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryListViewModel : ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<CountryModel>?>

    init {
        liveDataList = MutableLiveData()
    }


    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>?> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = ApiClient.getRetroInstance()
        val retroService = retroInstance.create(Service::class.java)
        val call = retroService.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
                Log.d("TAG", "${t.message}")
            }

            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
                Log.d("TAG", "tttt")
            }
        })
    }
}

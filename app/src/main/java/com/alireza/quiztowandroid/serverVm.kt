package com.alireza.quiztowandroid

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class serverVm {
    
    private val apiKey = "1c04e05bce6e626247758d120b372a73"
    private val method = "flickr.photos.search"
    private val userId = "34427466731@N01"
    private val extras = "url_s"
    private val format = "json"
    private val nojsoncallback = "1"
    private val perPage = "100"
    private val page = "1"
    
    
   // var list: LiveData<List<PhotoX>>
      
    init {
        getDataFromServer()
    }


    private fun getDataFromServer() : List<PhotoX>? {

        var lists:List<PhotoX>? = null
        RetrofitFlickr.service.loadSearches(
            apiKey,
            userId,
            method,
            extras,
            format,
            nojsoncallback,
            perPage,
            page
        ).enqueue(object : Callback<Photo> {
            override fun onResponse(call: Call<Photo>, response: Response<Photo>
            ) {
                lists = response.body()?.photos?.photo!!
                lists!!.forEach {
                    Log.d("Response",it.toString())
                }
            }

            override fun onFailure(call: Call<Photo>, t: Throwable) {
                t.message?.let { Log.d("Response", "onFailure$it") }
            }

        })

        return lists
    }
   
    
    
}
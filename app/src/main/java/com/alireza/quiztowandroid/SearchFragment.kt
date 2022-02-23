package com.alireza.quiztowandroid

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {


    private val apiKey = "1c04e05bce6e626247758d120b372a73"
    private val method = "flickr.photos.search"
    private val userId = "34427466731@N01"
    private val extras = "url_s"
    private val format = "json"
    private val nojsoncallback = "1"
    private val perPage = "100"
    private val page = "1"
    var lists:List<PhotoX>? = null

    lateinit var photoList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        lists = getDataFromServer()
    }

    private fun getDataFromServer() :List<PhotoX>?{

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


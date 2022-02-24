package com.alireza.quiztowandroid

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    var lists:List<PhotoX>? = null

    val serverVm:serverVm by viewModels()

    lateinit var photoList:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoList = view.findViewById(R.id.recyclerView)
        serverVm.list.observe(viewLifecycleOwner, Observer {
            photoList.adapter =  PhotoRecyclerAdapter(it)
            photoList.layoutManager = LinearLayoutManager(requireContext())
        })
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


}
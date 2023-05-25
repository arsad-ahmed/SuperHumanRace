package com.example.superhuman.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.superhuman.R
import com.example.superhuman.databinding.FragmentHomeBinding
import com.example.superhuman.model.DataX
import com.example.superhuman.model.DataXX
import com.example.superhuman.model.HealthModel
import com.example.superhuman.model.RequestBody
import com.example.superhuman.util.ACTION
import com.example.superhuman.util.LIMIT
import com.example.superhuman.util.LOADING_ANNOTATION
import com.example.superhuman.util.Resource
import com.example.superhuman.viewmodel.HealthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class HomeFragment:Fragment()
{
    private val healthViewModel by viewModels<HealthViewModel> ()
    private lateinit var listItemAdapter:ListItemAdapter
    private lateinit var binding:FragmentHomeBinding
    private val healthModel = mutableListOf<DataX>()

    @Inject
    @Named(LOADING_ANNOTATION)
    lateinit var loadingDialog:Dialog

    override fun onCreate(savedInstanceState:Bundle?)
    {
        super.onCreate(savedInstanceState)
        getDataFromApi(0)
    }

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View?
    {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        setRecyclerView()
        observeLiveData()
        initScrollListener()
        return binding.root
    }

    private fun setRecyclerView()
    {
        listItemAdapter=ListItemAdapter()
        binding.recyclerView.adapter=listItemAdapter
    }

    private fun observeLiveData()
    {
        healthViewModel.healthLiveData.observe(viewLifecycleOwner) {
            when(it)
            {
                is Resource.Loading ->
                {
                    loadingDialog.show()
                }
                is Resource.Success ->
                {
                    loadingDialog.hide()
                    val list=it.data!!.data.data
                    healthModel.addAll(list)
                    listItemAdapter.addProducts(list)

                }
                is Resource.Error ->
                {
                    loadingDialog.hide()
                    Toast.makeText(requireContext(), "Something went wrong,please again later", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initScrollListener()
    {
        val recyclerView=binding.recyclerView
        recyclerView.addOnScrollListener(object : OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == healthModel.size-1)
                    {
                        loadingDialog.show()
                        val id=healthModel[healthModel.size-1].id
                        getDataFromApi(id)

                    }
            }
        })
    }

    private fun getDataFromApi(id:Int)
    {
        healthViewModel.getHealthData(RequestBody(ACTION, DataXX(LIMIT,id)))
    }

}
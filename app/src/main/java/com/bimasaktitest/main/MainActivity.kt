package com.bimasaktitest.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bimasaktitest.R
import com.bimasaktitest.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = viewModel

        adapter = MainAdapter()
        adapter.setViewModel(viewModel, this)
        adapter.setListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick() {
                viewModel.getAllLocalData()
            }
        })

        binding.imageviewMainRefresh.setOnClickListener {
            viewModel.deleteAllData()
            viewModel.getAllLocalData()
        }

        setupSwipeRefresh()
        setupRecyclerView()
        observeLiveData()

        viewModel.getListData()
        viewModel.getAllLocalData()
    }

    private fun observeLiveData() {
        viewModel.isLoading.observe(this, Observer {
            binding.swipeRefreshMain.isRefreshing = it
        })
        viewModel.liveDataList.observe(this, Observer {
            Log.d("raw_response", it.results.toString())
            adapter.submitList(it.results)
        })
        viewModel.localLiveDataList.observe(this, Observer {
            Log.d("local_data", it.toString())
            adapter.submitLocalData(it)
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerMain.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshMain.setOnRefreshListener {
            viewModel.getListData()
            viewModel.getAllLocalData()
        }
    }
}

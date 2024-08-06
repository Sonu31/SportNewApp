package com.example.sportapps.ui.mainScreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapps.databinding.ActivityMainBinding
import com.example.sportapps.Application
import com.example.sportapps.response.Data
import com.example.sportapps.ui.mainScreen.adapter.SportListAdapter
import com.example.sportapps.ui.mainScreen.viewmodel.MainViewModel
import com.example.sportapps.ui.mainScreen.viewmodel.MainViewModelFactory
import com.example.sportapps.utils.ItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var newsListAdapter: SportListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as Application).repository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        binding.searchView.queryHint = "Search Sport Name"

        setupRecyclerView()
        setupObservers()
        setupSearchView()

        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.getEvent()
        }
    }

    private fun setupRecyclerView() {
        newsListAdapter = SportListAdapter(emptyList(), this, object : ItemClickListener {
            override fun onClick(eventModel: Data, position: Int) {
                // Remove the item from the adapter's data list
                val updatedList = newsListAdapter.list.toMutableList()
                updatedList.removeAt(position)
                newsListAdapter.updateData(updatedList)

                // Delete the item in ViewModel
                CoroutineScope(Dispatchers.IO).launch {
                    mainViewModel.delete(eventModel)
                }
            }
        })

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsListAdapter
        }
    }

    private fun setupObservers() {
        mainViewModel.eventLivedata.observe(this, Observer { data ->
            newsListAdapter.updateData(data)
            binding.pv.visibility = View.GONE
        })
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsListAdapter.filter.filter(newText)
                return false
            }
        })
    }
}

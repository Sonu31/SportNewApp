package com.example.sportapps.ui.mainScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapps.R
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

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    var newsListAdapter:SportListAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = (application as Application).repository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        binding.searchView.queryHint = "Search Sport Name"
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.getEvent()
        }

        mainViewModel.eventLivedata.observe(this, Observer {
            newsListAdapter = SportListAdapter(it,this, object : ItemClickListener {
                override fun onClick(eventmodel: Data,position:Int) {
                    mainViewModel.delete(eventmodel)
                    newsListAdapter?.notifyItemRemoved(position)

                }
            })

            binding.rv.adapter = newsListAdapter
            binding.pv.visibility = View.GONE
            binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val itemb = it.distinct()
                    newsListAdapter?.adapterSetData(itemb as ArrayList<Data>)
                    newsListAdapter?.filter?.filter(newText)
                    return false
                }

            })
        })

//        used filter
//        CoroutineScope(Dispatchers.IO).launch {
//            mainViewModel.getFilterEvent("Soccer")
//        }



    }
}
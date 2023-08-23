package com.example.pokemon.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.databinding.ActivityDetailBinding
import com.example.pokemon.network.ApiService
import com.example.pokemon.network.Resource
import com.example.pokemon.repositori.Repository
import com.example.pokemon.response.DetailResponse
import com.example.pokemon.response.PokemonResponse
import com.example.pokemon.view.main.MainAdapter
import com.example.pokemon.view.main.MainViewModel
import com.example.pokemon.view.main.MainViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val api by lazy { ApiService.getClient() }
    private lateinit var viewModel: DetailViewModel
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: DetailViewModelFactory

    private lateinit var adapter: AbilityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupListener()
        setupObserver()

    }

    private fun setupObserver() {
        viewModel.detail.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is Resource.Loading -> {
                    isLoad(true)
                    Log.d("Pokemon", " :: Loading")
                }

                is Resource.Success -> {
                    isLoad(false)
                    Log.d("Pokemon", " :: response :: ${it.data!!.abilities}")
                    adapter.setData(it.data.abilities)
                    Glide.with(this).load(it.data.sprites.front_default).into(binding.imageView)
                }

                is Resource.Error -> {
                    isLoad(false)
                    Log.d("Pokemon", " :: Error")
                }
            }
        })
        adapter = AbilityAdapter(arrayListOf(), object : AbilityAdapter.OnAdapterListener {
            override fun onClick(result: DetailResponse.Ability) {
            }
        })
        binding.recyclerView.adapter = adapter

    }

    private fun isLoad(load: Boolean) {

    }

    private fun setupViewModel() {
        repository = Repository(api)
        viewModelFactory = DetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }


    private fun setupListener() {
        var nama = intent.getStringExtra("nama")
        viewModel.fetchDetail(nama.toString())

        // Inisialisasi toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "$nama"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24) // Ganti dengan ikon back Anda
        }

        // Inisialisasi RecyclerView
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        val adapter = RecyclerViewAdapter() // Ganti dengan adapter RecyclerView Anda
//        binding.recyclerView.adapter = adapter
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
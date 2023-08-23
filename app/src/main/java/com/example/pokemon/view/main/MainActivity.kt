package com.example.pokemon.view.main

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.database.PokemonContract
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.network.ApiService
import com.example.pokemon.network.Resource
import com.example.pokemon.repositori.Repository
import com.example.pokemon.response.PokemonResponse
import com.example.pokemon.util.PokemonDbHelper
import com.example.pokemon.view.detail.DetailActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val api by lazy { ApiService.getClient() }
    private lateinit var viewModel: MainViewModel
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var adapter: MainAdapter
    private var isAscending = true

    var idContent = 0
    var endOfPage = false
    var onLoad = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupListener()
        setupObserver()


    }

    data class SimplePokemon(val name: String)

    private fun setupObserver() {
        viewModel.pokemon.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is Resource.Loading -> {
                    isLoad(true)
                    Log.d("Pokemon", " :: Loading")
                }

                is Resource.Success -> {
                    isLoad(false)
                    Log.d("Pokemon", " :: response :: ${it.data!!.results}")
                    adapter.setData(it.data.results)

                    // Simpan data ke database
                    val dbHelper = PokemonDbHelper(this@MainActivity)
                    val db = dbHelper.writableDatabase
                    for (result in it.data.results) {
                        val values = ContentValues().apply {
                            put(PokemonContract.PokemonEntry.COLUMN_NAME, result.name)
                            // Isi dengan kolom lainnya sesuai kebutuhan
                        }
                        db.insert(PokemonContract.PokemonEntry.TABLE_NAME, null, values)
                    }
                }


                is Resource.Error -> {
                    isLoad(false)
                    Log.d("Pokemon", " :: Error")

                    adapter.clear()

                    // Jika koneksi internet tidak tersedia, tampilkan data dari SQLite
                    val dbHelper = PokemonDbHelper(this@MainActivity)
                    val db = dbHelper.readableDatabase
                    val projection = arrayOf(PokemonContract.PokemonEntry.COLUMN_NAME)
                    val cursor = db.query(
                        PokemonContract.PokemonEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                    )


                    val pokemonNames = mutableListOf<PokemonResponse.Result>()
                    with(cursor) {
                        while (moveToNext()) {
                            val name =
                                getString(getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_NAME))
                            val result = PokemonResponse.Result(name, "") // Buat objek Result
                            pokemonNames.add(result)
                        }
                    }
                    adapter.setData(pokemonNames)


                }
            }
        })
        adapter = MainAdapter(arrayListOf(),
            object : MainAdapter.OnAdapterListener {
                override fun onClick(result: PokemonResponse.Result) {
                    startActivity(
                        Intent(this@MainActivity, DetailActivity::class.java).putExtra(
                            "nama",
                            result.name
                        )
                    )
                }
            })

        binding.recyclerView.adapter = adapter
    }

    private fun isLoad(load: Boolean) {
        if (load) {
            binding.progres.visibility = View.VISIBLE
        } else {
            binding.progres.visibility = View.GONE
        }

    }

    private fun setupViewModel() {
        repository = Repository(api)
        viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun setupListener() {
        setSupportActionBar(binding.toolbar)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.fetchPokemon(idContent)



        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    if (!endOfPage) {
                        if (!onLoad) {
                            idContent = idContent + 20
                            viewModel.fetchPokemon(idContent)
                        }
                    }
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort -> {
                adapter.sortData(true)
                return true
            }

            R.id.action_sort_z_a -> {
                adapter.sortData(false)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
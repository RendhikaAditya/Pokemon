package com.example.pokemon.view.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.network.Resource
import com.example.pokemon.repositori.Repository
import com.example.pokemon.response.PokemonResponse
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: Repository
) : ViewModel() {
    val pokemon: MutableLiveData<Resource<PokemonResponse>> = MutableLiveData()

    fun fetchPokemon(offset: Int) = viewModelScope.launch {
        pokemon.value = Resource.Loading()
        try {

                val response = repository.fetchGetPokemon(offset)
                val responseData = response.body()!!
                pokemon.value = Resource.Success(responseData)


        } catch (e: Exception) {
            pokemon.value = Resource.Error(e.message.toString())
        }

    }
}

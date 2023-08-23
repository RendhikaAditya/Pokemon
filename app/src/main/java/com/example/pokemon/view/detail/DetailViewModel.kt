package com.example.pokemon.view.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.network.Resource
import com.example.pokemon.repositori.Repository
import com.example.pokemon.response.DetailResponse
import com.example.pokemon.response.PokemonResponse
import kotlinx.coroutines.launch

class DetailViewModel(
    val repository: Repository
) : ViewModel() {
    val detail: MutableLiveData<Resource<DetailResponse>> = MutableLiveData()

    fun fetchDetail(nama: String) = viewModelScope.launch {
        detail.value = Resource.Loading()
        try {

                val response = repository.fetchGetDetailPokemon(nama)
                val responseData = response.body()!!
                detail.value = Resource.Success(responseData)


        } catch (e: Exception) {
            detail.value = Resource.Error(e.message.toString())
        }

    }
}

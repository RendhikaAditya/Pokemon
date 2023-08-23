package com.example.pokemon.repositori

import com.example.pokemon.api.Api
import com.example.pokemon.response.PokemonResponse
import retrofit2.Response

class Repository(private val api: Api) {
    suspend fun fetchGetPokemon(offset: Int): Response<PokemonResponse> {
        return api.getPokemonList(offset)
    }

    suspend fun fetchGetDetailPokemon(name:String) = api.getPokemonDetail(name)
}

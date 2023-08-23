package com.example.pokemon.database

import android.provider.BaseColumns

object PokemonContract {
    class PokemonEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "pokemon"
            const val COLUMN_NAME = "name"
            // Tambahkan kolom lain yang diperlukan sesuai kebutuhan
        }
    }
}
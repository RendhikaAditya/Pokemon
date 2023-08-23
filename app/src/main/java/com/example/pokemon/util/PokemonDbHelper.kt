package com.example.pokemon.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.pokemon.database.PokemonContract

class PokemonDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_POKEMON_TABLE = """
            CREATE TABLE ${PokemonContract.PokemonEntry.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${PokemonContract.PokemonEntry.COLUMN_NAME} TEXT NOT NULL
                -- Tambahkan kolom lain yang diperlukan sesuai kebutuhan
            );
        """
        db.execSQL(SQL_CREATE_POKEMON_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${PokemonContract.PokemonEntry.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "pokemon.db"
        const val DATABASE_VERSION = 1
    }
}

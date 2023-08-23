package com.example.pokemon.util

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Helper {
    fun showTolakPeminjamanDialog(context: Context) {
        val builder = AlertDialog.Builder(context)

        // Mengatur judul dialog
        builder.setTitle("Tolak Peminjaman")

        // Mengatur teks dalam dialog
        builder.setMessage("Yakin Menolak Peminjaman kendaraan ini?")

        // Menambahkan tombol positif dengan teks "Yakin"
        builder.setPositiveButton("Yakin") { dialog, which ->

        }

        // Menambahkan tombol negatif dengan teks "Tidak"
        builder.setNegativeButton("Tidak") { dialog, which ->
            // Aksi yang akan dilakukan ketika tombol "Tidak" diklik
            // Anda dapat menambahkan logika lain jika diperlukan
            dialog.dismiss()
        }

        // Membuat dan menampilkan dialog
        val dialog = builder.create()
        dialog.show()
    }

    fun convertBulan(angkaBulan: Int): String {
        val bulan = arrayOf(
            "", "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )

        return bulan[angkaBulan]
    }

    fun generateHari(tanggal: Int, bulan: Int, tahun: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(tahun, bulan - 1, tanggal)

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val hari = SimpleDateFormat("EEEE", Locale("id", "ID"))
        return hari.format(calendar.time)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun ubahFormatTanggal(tanggal: String): String {
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.getDefault())
        val date = LocalDate.parse(tanggal, inputFormat)
        return date.format(outputFormat)
    }

}
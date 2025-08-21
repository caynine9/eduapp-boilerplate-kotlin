package com.aplikasi.bhd.model

import com.aplikasi.bhd.model.SimulasiStep

data class Simulasi(
    val id: Int,
    val judul: String,
    val deskripsi: String,
    val gambar: String,
    val steps: List<SimulasiStep>
)

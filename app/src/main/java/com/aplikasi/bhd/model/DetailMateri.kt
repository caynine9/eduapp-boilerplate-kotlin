package com.aplikasi.bhd.model

data class DetailMateri(
    val materiId: String,
    val details: List<DetailStep>
)

data class DetailStep(
    val step: Int,
    val title: String,
    val content: String
)

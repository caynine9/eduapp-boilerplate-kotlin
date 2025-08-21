package com.aplikasi.bhd.ui.belajar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.bhd.R
import com.aplikasi.bhd.databinding.ActivityDetailMateriBinding
import com.aplikasi.bhd.model.DetailMateri
import com.aplikasi.bhd.model.DetailStep
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailMateriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMateriBinding
    private lateinit var detailAdapter: DetailMateriAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val materiId = intent.getStringExtra("materiId") ?: ""
        val judul = intent.getStringExtra("judul") ?: ""
        val gambar = intent.getStringExtra("gambar") ?: ""

        binding.tvJudulMateri.text = judul
        if (gambar.isNotEmpty()) {
            Glide.with(this)
                .load("file:///android_asset/pic/$gambar")
                .placeholder(R.drawable.ic_placeholder_foreground)
                .into(binding.ivDetailMateri)
        }

        val detailList = readDetailMateriJson(materiId)
        detailAdapter = DetailMateriAdapter(detailList)
        binding.rvDetailMateri.layoutManager = LinearLayoutManager(this)
        binding.rvDetailMateri.adapter = detailAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun readDetailMateriJson(materiId: String): List<DetailStep> {
        val jsonString = assets.open("json/belajar/detail_materi.json").bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<DetailMateri>>() {}.type
        val allDetailMateri: List<DetailMateri> = Gson().fromJson(jsonString, listType)
        val detailMateri = allDetailMateri.find { it.materiId == materiId }
        return detailMateri?.details ?: emptyList()
    }
}
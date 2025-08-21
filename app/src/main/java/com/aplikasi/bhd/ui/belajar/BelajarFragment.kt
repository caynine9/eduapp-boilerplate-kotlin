package com.aplikasi.bhd.ui.belajar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.bhd.R
import com.aplikasi.bhd.databinding.FragmentBelajarBinding
import com.aplikasi.bhd.model.Materi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Pada belajarFragment akan menggunakan TabLayout untuk menampilkan beberapa tab
// yang berisi materi belajar. Setiap tab akan menampilkan kategori konten yang berbeda.
// Kemudian RecyclerView akan digunakan untuk menampilkan daftar materi belajar
// yang tersedia dalam kategori tersebut. Setiap item dalam RecyclerView akan berisi judul materi
// dan deskripsi singkat. Ketika pengguna mengklik item, akan membuka halaman detail materi
// yang berisi konten lengkap, gambar, dan video jika tersedia.
// Terdapat juga fitur pencarian untuk memudahkan pengguna menemukan materi tertentu.


class BelajarFragment : Fragment() {

    private var _binding: FragmentBelajarBinding? = null
    private val binding get() = _binding!!

    private lateinit var materiAdapter: MateriAdapter
    private var materiList: List<Materi> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBelajarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchComponent()
        materiList = readMateriJson()
        materiAdapter = MateriAdapter(materiList) { materi ->
            val intent = android.content.Intent(requireContext(), DetailMateriActivity::class.java)
            // kirim id.materi.toString() sebagai extra ke DetailMateriActivity menjadi "materiId"
            // kemudian filter detail materi dengan key "materiId"
            intent.putExtra("materiId", materi.id.toString())

            intent.putExtra("judul", materi.judul)
            intent.putExtra("gambar", materi.gambar)
            startActivity(intent)
        }
        binding.recyclerViewMateri.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMateri.adapter = materiAdapter
    }

    fun initSearchComponent() {
        val searchEditText = binding.searchEditText
        val searchClear = binding.searchClear

        // Tampilkan tombol clear jika ada teks, sembunyikan jika kosong
        searchEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                searchClear.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Klik tombol clear untuk menghapus teks
        searchClear.setOnClickListener {
            searchEditText.text.clear()
        }

        // Ubah warna text dan hint sesuai fokus
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                searchEditText.setTextColor(resources.getColor(R.color.black, null))
                searchEditText.setHintTextColor(resources.getColor(R.color.black, null))
            } else {
                searchEditText.setTextColor(resources.getColor(R.color.light_gray, null))
                searchEditText.setHintTextColor(resources.getColor(R.color.light_gray, null))
            }
        }
    }

    fun readMateriJson(): List<Materi> {
        val jsonString = requireContext().assets.open("json/belajar/materi.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, object : TypeToken<List<Materi>>() {}.type)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
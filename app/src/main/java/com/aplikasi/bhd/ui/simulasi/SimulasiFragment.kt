package com.aplikasi.bhd.ui.simulasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.bhd.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.aplikasi.bhd.databinding.FragmentSimulasiBinding
import com.aplikasi.bhd.model.Simulasi

// TODO: Pada simulasiFragment ini, nanti akan menggunakan TabLayout untuk menampilkan beberapa tab
// yang berisi materi sumlasi. Setiap tab akan menampilkan kategori konten yang berbeda.
// Kemudian RecyclerView akan digunakan untuk menampilkan daftar materi simulasi
// yang tersedia dalam kategori tersebut. Setiap item dalam RecyclerView akan berisi judul simulasi
// dan deskripsi singkat. Ketika pengguna mengklik item, akan membuka halaman simulasi menggunakan ViewPager/RecyclerView
// yang akan menampilkan konten simulasi secara interaktif dan step-by-step. Untuk setiap simulasi,
// akan ada penjelasan yang mendetail tentang langkah-langkah yang harus diikuti. User dapat berinteraksi dengan simulasi
// dan mengklik tombol untuk melanjutkan ke langkah berikutnya.

class SimulasiFragment : Fragment() {
    private var _binding: FragmentSimulasiBinding? = null
    private val binding get() = _binding!!

    private lateinit var simulasiAdapter: SimulasiAdapter
    private var simulasiList: List<Simulasi> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimulasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchComponent()
        simulasiList = readSimulasiJson()
        simulasiAdapter = SimulasiAdapter(simulasiList) { simulasi ->
            val intent = android.content.Intent(requireContext(), SimulasiStepActivity::class.java)
            // kirim id.simulasi.toString() sebagai extra ke SimulasiStepActivity
            intent.putExtra("simulasiId", simulasi.id.toString())
            intent.putExtra("judul", simulasi.judul)
            intent.putExtra("gambar", simulasi.gambar)
            startActivity(intent)
        }
        binding.recyclerViewSimulasi.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSimulasi.adapter = simulasiAdapter
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

    fun readSimulasiJson(): List<Simulasi> {
        val jsonString = requireContext().assets.open("json/simulasi/simulasi.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, object : TypeToken<List<Simulasi>>() {}.type)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
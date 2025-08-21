package com.aplikasi.bhd.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aplikasi.bhd.R
import com.aplikasi.bhd.databinding.FragmentHomeBinding
import com.aplikasi.bhd.ui.belajar.BelajarFragment
import com.aplikasi.bhd.ui.kuis.KuisFragment
import com.aplikasi.bhd.ui.simulasi.SimulasiFragment
import com.aplikasi.bhd.ui.riwayat.RiwayatFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()

        val belajarFragment = BelajarFragment()
        val simulasiFragment = SimulasiFragment()
        val kuisFragment = KuisFragment()
        val riwayatFragment = RiwayatFragment()

        super.onViewCreated(view, savedInstanceState)
        binding.cardBelajar.setOnClickListener {
            transaction?.replace(R.id.fragment_container_view, belajarFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        binding.cardSimulasi.setOnClickListener {
            transaction?.replace(R.id.fragment_container_view, simulasiFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }
}
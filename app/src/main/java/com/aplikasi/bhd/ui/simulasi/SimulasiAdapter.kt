package com.aplikasi.bhd.ui.simulasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.bhd.R
import com.aplikasi.bhd.model.Simulasi
import com.bumptech.glide.Glide

class SimulasiAdapter(
    private val items: List<Simulasi>,
    private val onItemClick: (Simulasi) -> Unit
) : RecyclerView.Adapter<SimulasiAdapter.SimulasiViewHolder>() {

    class SimulasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val judul: TextView = itemView.findViewById(R.id.tv_judul_simulasi)
        val deskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi_simulasi)
        val gambar: ImageView = itemView.findViewById(R.id.iv_gambar_simulasi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimulasiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simulasi, parent, false)
        return SimulasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimulasiViewHolder, position: Int) {
        val simulasi = items[position]
        holder.judul.text = simulasi.judul
        holder.deskripsi.text = simulasi.deskripsi
        Glide.with(holder.itemView.context)
            .load("file:///android_asset/pic/${simulasi.gambar}")
            .placeholder(R.drawable.ic_placeholder_foreground)
            .error(R.mipmap.ic_error_image)
            .into(holder.gambar)
        holder.itemView.setOnClickListener {
            onItemClick(simulasi)
        }
    }
    override fun getItemCount(): Int = items.size
}
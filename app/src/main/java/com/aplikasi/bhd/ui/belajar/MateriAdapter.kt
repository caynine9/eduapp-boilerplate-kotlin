package com.aplikasi.bhd.ui.belajar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.bhd.R
import com.aplikasi.bhd.model.Materi
import com.bumptech.glide.Glide

class MateriAdapter(
    private val items: List<Materi>,
    private val onItemClick: (Materi) -> Unit
) : RecyclerView.Adapter<MateriAdapter.MateriViewHolder>() {
    class MateriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val judul: TextView = itemView.findViewById(R.id.tv_judul_materi)
        val deskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi_materi)
        val gambar: ImageView = itemView.findViewById(R.id.iv_gambar_materi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_materi, parent, false)
        return MateriViewHolder(view)
    }

    override fun onBindViewHolder(holder: MateriViewHolder, position: Int) {
        val materi = items[position]
        holder.judul.text = materi.judul
        holder.deskripsi.text = materi.deskripsi
        Glide.with(holder.itemView.context)
            .load("file:///android_asset/pic/${materi.gambar}")
            .placeholder(R.drawable.ic_placeholder_foreground)
            .error(R.mipmap.ic_error_image)
            .into(holder.gambar)
        holder.itemView.setOnClickListener {
            onItemClick(materi)
        }
    }

    override fun getItemCount(): Int = items.size
}

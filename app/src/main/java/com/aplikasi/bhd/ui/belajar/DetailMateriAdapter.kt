package com.aplikasi.bhd.ui.belajar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.bhd.R
import com.aplikasi.bhd.model.DetailStep

class DetailMateriAdapter(private val items: List<DetailStep>) : RecyclerView.Adapter<DetailMateriAdapter.DetailViewHolder>() {
    private val expandedPositionSet = mutableSetOf<Int>()

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStepTitle: TextView = itemView.findViewById(R.id.tv_step_title)
        val tvStepContent: TextView = itemView.findViewById(R.id.tv_step_content)
        val ivExpand: ImageView = itemView.findViewById(R.id.iv_expand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_materi, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val item = items[position]
        holder.tvStepTitle.text = "Langkah ${item.step}. ${item.title}"
        holder.tvStepContent.text = item.content

        val expanded = expandedPositionSet.contains(position)
        holder.tvStepContent.visibility = if (expanded) View.VISIBLE else View.GONE
        holder.ivExpand.setImageResource(if (expanded) R.drawable.ic_expand_less else R.drawable.ic_expand_more)

        holder.ivExpand.setOnClickListener {
            if (expanded) expandedPositionSet.remove(position) else expandedPositionSet.add(position)
            notifyItemChanged(position)
        }
        holder.tvStepTitle.setOnClickListener {
            if (expanded) expandedPositionSet.remove(position) else expandedPositionSet.add(position)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = items.size
}


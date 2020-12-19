package com.ananda.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ananda.oop2.Database.PS
import kotlinx.android.synthetic.main.adapter_ps.view.*


class PSAdapter (private val AllPS: ArrayList<PS>, private val listener: OnAdapterListener) : RecyclerView.Adapter<PSAdapter.PSViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PSViewHolder {
        return PSViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_ps, parent, false)
        )
    }

    override fun getItemCount() = AllPS.size

    override fun onBindViewHolder(holder: PSViewHolder, position: Int) {
        val ps = AllPS[position]
        holder.view.text_durasi.text = ps.durasi
        holder.view.text_durasi.setOnClickListener {
            listener.onClick(ps)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(ps)
        }
    }

    class PSViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<PS>) {
        AllPS.clear()
        AllPS.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(ps: PS)
        fun onDelete(ps: PS)
    }

}
package com.ananda.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pemilik.view.*

class PemilikAdapter(val pemilik : ArrayList<Pemilik>, val onClick : OnClick) : RecyclerView.Adapter<PemilikAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pemilik, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = pemilik.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(pemilik.get(position))
        holder.itemView.btDeletePemilik.setOnClickListener {
            onClick.delete(pemilik.get(position).key)
        }
        holder.itemView.setOnClickListener {
            onClick.edit(pemilik.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pemilik: Pemilik) {
            itemView.tvPemilikName.text = pemilik.nama
            itemView.tvPemilikDescription.text = pemilik.id
        }
    }

    interface OnClick {
        fun delete(key: String?)
        fun edit(pemilik: Pemilik?)
    }

}
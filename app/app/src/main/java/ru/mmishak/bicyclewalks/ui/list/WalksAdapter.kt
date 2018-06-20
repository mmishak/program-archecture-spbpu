package ru.mmishak.bicyclewalks.ui.list

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.mmishak.bicyclewalks.Config
import ru.mmishak.bicyclewalks.R
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation.BicycleWalk
import ru.mmishak.bicyclewalks.ui.walk.BicycleWalkActivity

class WalksAdapter(var items: MutableList<BicycleWalkEntity>) : RecyclerView.Adapter<WalkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalkViewHolder {
        return WalkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_walk, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WalkViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, BicycleWalkActivity::class.java)
            intent.putExtra("walk_id", items[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }
}
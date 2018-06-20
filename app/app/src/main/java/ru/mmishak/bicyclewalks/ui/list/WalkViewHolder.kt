package ru.mmishak.bicyclewalks.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_walk.view.*
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation.BicycleWalk

class WalkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: BicycleWalkEntity) {
        itemView.title.text = data.title
        itemView.status.text = getStatus(data.leaderStatus)
    }

    private fun getStatus(leaderStatus: LeaderStatus): String {
        return when (leaderStatus) {
            LeaderStatus.WAITING_ACCEPT -> "Ожидает подтверждения"
            LeaderStatus.ACCEPTED -> "Подтверждено"
            LeaderStatus.WITHOUT_LEADER -> "Без ведущего"
            LeaderStatus.REJECTED -> "Отклонено"
        }
    }
}
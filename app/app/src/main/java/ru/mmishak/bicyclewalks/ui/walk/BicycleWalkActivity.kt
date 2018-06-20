package ru.mmishak.bicyclewalks.ui.walk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_bicycle_walk.*
import ru.mmishak.bicyclewalks.Config
import ru.mmishak.bicyclewalks.R
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader
import ru.mmishak.bicyclewalks.domain.repositories.implementation.BicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.repositories.implementation.LeaderRepository

class BicycleWalkActivity : AppCompatActivity() {

    private lateinit var walk: BicycleWalkEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicycle_walk)

        walk = BicycleWalkRepository().get(intent.getIntExtra("walk_id", 0))!!

        title_label.text = walk.title
        desc.text = walk.description
        status.text = getStatus(walk.leaderStatus)

        if (Config.isLeaderCurrent) {
            reject.visibility = View.VISIBLE
            accept.visibility = View.VISIBLE
        } else {
            reject.visibility = View.GONE
            accept.visibility = View.GONE
        }

        reject.setOnClickListener {
            val leader = Config.currentUser as Leader
            leader.rejectWalkRequest(walk)
            BicycleWalkRepository().saveChanges(walk)
            onBackPressed()
        }

        accept.setOnClickListener {
            val leader = Config.currentUser as Leader
            leader.acceptWalkRequest(walk)
            BicycleWalkRepository().saveChanges(walk)
            onBackPressed()
        }
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

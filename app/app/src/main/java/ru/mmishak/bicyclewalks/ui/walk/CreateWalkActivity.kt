package ru.mmishak.bicyclewalks.ui.walk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_walk.*
import ru.mmishak.bicyclewalks.Config
import ru.mmishak.bicyclewalks.R
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer
import ru.mmishak.bicyclewalks.domain.repositories.implementation.LeaderRepository
import ru.mmishak.bicyclewalks.domain.repositories.implementation.OrganizerRepository
import ru.mmishak.bicyclewalks.domain.util.DateTimeHelper

class CreateWalkActivity : AppCompatActivity() {

    private val leader = LeaderRepository().getAll()[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_walk)


        label_leader_value.text = leader.firstName

        create_btn.setOnClickListener {
            val title = input_title.text.toString()
            val desc = input_desc.text.toString()
            val organizer = Config.currentUser as Organizer
            organizer.createWalk(title = title, description = desc, leader = leader, walkType = WalkType.WALK, date = DateTimeHelper.dateToMillis("20.05.2018"), price = 0, distance = 14, duration = DateTimeHelper.hourToMillis(4))
            Toast.makeText(this, "Прогулка создана", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }
}

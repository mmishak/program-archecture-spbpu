package ru.mmishak.bicyclewalks.ui.list

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_walk_list.*
import ru.mmishak.bicyclewalks.Config
import ru.mmishak.bicyclewalks.R
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer
import ru.mmishak.bicyclewalks.service.usecases.BicycleWalksList
import ru.mmishak.bicyclewalks.ui.walk.CreateWalkActivity

class WalkListActivity : AppCompatActivity() {

    private lateinit var adapter: WalksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_list)

        if (Config.isLeaderCurrent) {
            title_label.text = "Велопрогулки ведущего"
            adapter = WalksAdapter(BicycleWalksList.getLeaderWalks(Config.currentUser as Leader).toMutableList())
        } else {
            title_label.text = "Велопрогулки организатора"
            adapter = WalksAdapter(BicycleWalksList.getOrganizerWalks(Config.currentUser as Organizer).toMutableList())
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()

        add_walk_btn.visibility = if (Config.isOrganizerCurrent) View.VISIBLE else View.GONE
        add_walk_btn.setOnClickListener {
            startActivity(Intent(this, CreateWalkActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.items = if (Config.isLeaderCurrent)
            BicycleWalksList.getLeaderWalks(Config.currentUser as Leader).toMutableList()
        else
            BicycleWalksList.getOrganizerWalks(Config.currentUser as Organizer).toMutableList()
        adapter.notifyDataSetChanged()
    }
}

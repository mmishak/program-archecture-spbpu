package ru.mmishak.bicyclewalks.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_select_user.*
import ru.mmishak.bicyclewalks.R
import ru.mmishak.bicyclewalks.service.usecases.Registration

class SelectUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user)

        generate_btn.setOnClickListener {
            Registration.registerLeader(
                    login = "good_leader", password = "abc", email = "a@b.ru", firstName = "Ivan", lastName = "Kozlov", phone = "+79998883322"
            )
            Registration.registerOrganizer(
                    login = "super_org", password = "abc", email = "a@b.ru", title = "Funny Company"
            )
            Toast.makeText(this, "Организатор и ведущий добавлены", Toast.LENGTH_SHORT).show()
        }

        organizer_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("type", "organizer")
            startActivity(intent)
        }

        leader_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("type", "leader")
            startActivity(intent)
        }
    }
}

package ru.mmishak.bicyclewalks.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import ru.mmishak.bicyclewalks.R
import ru.mmishak.bicyclewalks.service.usecases.Authentication
import ru.mmishak.bicyclewalks.ui.list.WalkListActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        type = intent.getStringExtra("type")

        sign_in_btn.setOnClickListener {
            val login = input_login.text.toString()
            val password = input_password.text.toString()
            when (type) {
                "organizer" -> {
                    if (Authentication.authOrganizer(login, password))
                        startActivity(Intent(this, WalkListActivity::class.java))
                    else
                        Toast.makeText(this, "Авторизация не удалась", Toast.LENGTH_SHORT).show()
                }
                "leader" -> {
                    if (Authentication.authLeader(login, password))
                        startActivity(Intent(this, WalkListActivity::class.java))
                    else
                        Toast.makeText(this, "Авторизация не удалась", Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(this, "Тип пользователя не выбран", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

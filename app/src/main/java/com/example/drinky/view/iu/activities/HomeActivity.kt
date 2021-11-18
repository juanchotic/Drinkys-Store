package com.example.drinky.view.iu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import com.example.drinky.R
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent




class HomeActivity : AppCompatActivity() {

    private lateinit var loginButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loginButton = findViewById(R.id.ButtonLogin)

        loginButton.setOnClickListener{
            view: View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish();
        }

    }

}
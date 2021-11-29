package com.example.drinky.view.iu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import com.example.drinky.R
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeActivity : AppCompatActivity() {

    //private lateinit var loginButton:Button
    //private lateinit var auth: FirebaseAuth

    private lateinit var carritoCompra: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        carritoCompra = findViewById(R.id.btnCarritoCompra)
        /*
        carritoCompra.setOnClickListener {
            view : view ->

        }*/

        /*
        auth = Firebase.auth

        loginButton = findViewById(R.id.ButtonLogin)

        loginButton.setOnClickListener{
            view: View ->
            Firebase.auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish();
        }*/

    }

}
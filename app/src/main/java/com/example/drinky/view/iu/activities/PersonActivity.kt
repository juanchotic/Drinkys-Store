package com.example.drinky.view.iu.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController
import com.example.drinky.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PersonActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var irhome : ImageButton
    private lateinit var botonSecion : Button
    private lateinit var instagram : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        auth = Firebase.auth

        irhome = findViewById(R.id.irAlHome)
        botonSecion  = findViewById(R.id.btnSecion)
        instagram = findViewById(R.id.button_insta)
        val currentUser = auth.currentUser
        if(currentUser != null){
            botonSecion.text = "Cerrar Secion"
        }
        else{
            botonSecion.text = "Iniciar Secion"
        }

        irhome.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish();
        }

        botonSecion.setOnClickListener{
            Firebase.auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish();
        }

         instagram.setOnClickListener{
            val open = Intent(android.content.Intent.ACTION_VIEW)
            open.data = Uri.parse("https://www.instagram.com/?hl=es-la")
             startActivity(open)

         }


    }


}
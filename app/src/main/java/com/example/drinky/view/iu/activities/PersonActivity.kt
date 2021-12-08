package com.example.drinky.view.iu.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.drinky.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PersonActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    private lateinit var irhome : ImageButton
    private lateinit var botonSecion : Button
    private lateinit var instagram : Button

    private lateinit var nombreUser:TextView
    private lateinit var correoUser:TextView
    private lateinit var telefonoUser:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        auth = Firebase.auth

        irhome = findViewById(R.id.irAlHome)
        botonSecion  = findViewById(R.id.btnSecion)
        instagram = findViewById(R.id.button_insta)

        nombreUser = findViewById(R.id.nombreUser)
        correoUser = findViewById(R.id.emailUserP)
        telefonoUser = findViewById(R.id.telefonoUser)

        val currentUser = auth.currentUser
        if(currentUser != null){

            db.collection("users")
                .get()
                .addOnSuccessListener { result ->

                    for(document in result){

                        if(document.data.getValue("email").toString() == auth.currentUser?.email.toString() ){
                            nombreUser.text = document.data.getValue("nombre").toString() + " " + document.data.getValue("apellido").toString()
                            correoUser.text = auth.currentUser?.email.toString()
                            telefonoUser.text = document.data.getValue("telefono").toString()
                            break
                        }

                    }

                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show()
                }

            botonSecion.text = "Cerrar Secion"
        }
        else{

            nombreUser.text = "NombreUser"
            correoUser.text = "correoUser@proveedor.co"
            telefonoUser.text = "##########"

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
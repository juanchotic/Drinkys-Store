package com.example.drinky.view.iu.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import com.example.drinky.R
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.drinky.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeActivity : AppCompatActivity() {

    //private lateinit var loginButton:Button
    private lateinit var auth: FirebaseAuth

    private lateinit var configuraciones: ImageButton
    private lateinit var scanerCodigo: ImageButton

    //private lateinit var binding: ActivityMainBinding


    //val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
    //val navController = navHostFragment.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        configNav ()

        configuraciones = findViewById(R.id.btnConfiguraciones)
        scanerCodigo = findViewById(R.id.btnScanProduct)

        configuraciones.setOnClickListener{
            val intent = Intent(this, PersonActivity::class.java)
            startActivity(intent)
            finish();
        }

        scanerCodigo.setOnClickListener{
            Toast.makeText(this, "Abrir camara", Toast.LENGTH_LONG).show()
        }

    }

    fun configNav () {
        val navHostFragnent = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragnent.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(
            navController
        )
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.ic_home -> {
                println("This is my home")
                true
            }
            R.id.ic_log -> {
                println("This is my Logo")
                true
            }
            R.id.ic_person -> {
                println("This is my person")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

}
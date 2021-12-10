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
//import androidx.camera.core.Camera
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
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


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
        this.setContentView(R.layout.activity_home)
        this.configNav()

        this.configuraciones = this.findViewById(R.id.btnConfiguraciones)
        this.scanerCodigo = this.findViewById(R.id.btnScanProduct)

        this.configuraciones.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            this.startActivity(intent)
            this.finish()
        }

        this.scanerCodigo.setOnClickListener {
            //Toast.makeText(this, "Soy el Scanner", Toast.LENGTH_LONG).show()
            this.barcodeLauncher.launch(ScanOptions())
        }

    }

    // Register the launcher and result handler
    private val barcodeLauncher = this.registerForActivityResult(
        ScanContract()
    ){ result : ScanIntentResult ->
        if(result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun configNav() {
        val navHostFragment =
            this.supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        this.findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(
            navController
        )
    }
}


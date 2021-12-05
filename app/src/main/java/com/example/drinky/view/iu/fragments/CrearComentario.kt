package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.drinky.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearComentario : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_comentario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        view?.findViewById<Button>(R.id.buttonabc2)?.setOnClickListener() {
            val nombreTxt = view?.findViewById<EditText>(R.id.editTextTextPersonName2)?.text
            val asuntoTxt = view?.findViewById<EditText>(R.id.editTextTextPersonName3)?.text
            val calificacionTxt = view?.findViewById<EditText>(R.id.editTextTextPersonName4)?.text
            val textoTxt = view?.findViewById<EditText>(R.id.editTextTextPersonName5)?.text

            var cal: Float = 0f
            if(calificacionTxt.toString() == ""){
                cal=0f;
            }
            else{
                cal = calificacionTxt.toString().toFloat()
            }


            val comentario = hashMapOf(
                "nombre" to nombreTxt.toString(),
                "asunto" to asuntoTxt.toString(),
                "calificacion" to cal,
                "texto" to textoTxt.toString()
            )

            db.collection("Comentarios")
                .add(comentario)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(
                        requireContext().applicationContext,
                        "Guardado",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG)
                        .show()
                }

            view.findNavController().navigate(R.id.action_crearComentario_to_comentariosFragment)
        }


    }
}
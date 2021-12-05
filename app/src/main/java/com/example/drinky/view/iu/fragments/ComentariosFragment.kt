package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.drinky.R
import com.example.drinky.view.iu.clases.Comentario
import com.example.drinky.view.iu.clases.ComentariosAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComentariosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComentariosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comentarios, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        val listView = view.findViewById<ListView>(R.id.list123)
        db.collection("Comentarios")
            .get()
            .addOnSuccessListener { result ->
                val listaModelos = arrayListOf<Comentario>()
                for (document in result) {
                    val modelo = Comentario()
                    modelo.nombre = document.data.getValue("nombre").toString()
                    modelo.asunto = document.data.getValue("asunto").toString()
                    modelo.calificacion = document.data.getValue("calificacion").toString().toFloat()
                    modelo.texto = document.data.getValue("texto").toString()

                    listaModelos.add(modelo)
                }
                val adapter = ComentariosAdapter(requireContext(), listView.id, listaModelos)
                listView.setAdapter(adapter)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG)
                    .show()
            }

        view?.findViewById<Button>(R.id.buttonabc)?.setOnClickListener() {
            view.findNavController().navigate(R.id.action_comentariosFragment_to_crearComentario)
        }
    }

}
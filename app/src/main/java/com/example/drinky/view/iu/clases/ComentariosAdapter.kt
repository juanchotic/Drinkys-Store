package com.example.drinky.view.iu.clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.example.drinky.R

class ComentariosAdapter(val thisContext: Context, val resource: Int, val comentarios: ArrayList<Comentario>) :
    ArrayAdapter<Comentario>(thisContext, resource, comentarios) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View

        if(convertView == null){
            view = LayoutInflater.from(thisContext).inflate(R.layout.comment_element, null)
        }
        else{
            view = convertView
        }

        var modelo = comentarios.get(position)

        var nombreTxt = view.findViewById<TextView>(R.id.nombreUser)
        nombreTxt.text = modelo.nombre

        var calificacionRB= view.findViewById<RatingBar>(R.id.ratingBar)
        calificacionRB.rating = modelo.calificacion

        var asuntoTxt = view.findViewById<TextView>(R.id.textView9)
        asuntoTxt.text = modelo.asunto

        var textoTxt = view.findViewById<TextView>(R.id.textView10)
        textoTxt.text = modelo.texto

        return view
    }

}
package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinky.R
import com.example.drinky.view.iu.clases.CarritoAdapter
import com.example.drinky.view.iu.clases.ListAdapter
import com.example.drinky.view.iu.clases.ListElement
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CarritoCompraFragment : Fragment(), CarritoAdapter.OnItemClickListenerDelete {

    // Listas
    private lateinit var element : List<ListElement>
    private lateinit var carrito : List<ListElement>

    // Firebase
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    // XML
    private lateinit var btnVaciar : Button
    private lateinit var btnPagar : Button
    private lateinit var totalPagar : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        element = ArrayList<ListElement>()
        carrito = ArrayList<ListElement>()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPagar = view.findViewById(R.id.btnPagar)
        btnVaciar = view.findViewById(R.id.btnVaciar)
        totalPagar = view.findViewById(R.id.totalPagar)

        val currentUser = auth.currentUser
        if(currentUser != null){
            init(view, currentUser.email.toString())
        }
        else {
            Toast.makeText(requireContext().applicationContext, "Debes iniciar secion", Toast.LENGTH_LONG).show()
        }


        btnVaciar.setOnClickListener {
            vaciar()
        }

        btnPagar.setOnClickListener {
            pagar()
        }


    }

    private fun pagar() {
    }

    private fun vaciar() {
    }

    private fun init(view:View, email:String){

        var i = 0

        db.collection("Productos").addSnapshotListener { result, error ->

            if( error != null ){
                return@addSnapshotListener
            }

            for(document in result!!){

                (element as ArrayList<ListElement>).add(i,
                    ListElement(
                        document.id,
                        document.data.getValue("precio").toString().toInt(),
                        document.data.getValue("popular").toString().toBoolean(),
                        document.data.getValue("nombre").toString(),
                        document.data.getValue("categoria").toString(),
                        document.data.getValue("descripcion").toString()
                    )
                )
                i += 1

            }

            db.collection("Carrito").addSnapshotListener{ resultC, errorC ->

                if( errorC != null ){
                    return@addSnapshotListener
                }

                var ic = 0

                for(document in resultC!!){

                    if( document.data.getValue("emailUser").toString() == email ){

                        for( x in 0..element.size-1 ){

                            if( document.data.getValue("idProducto").toString() == element[x].idProducto ){
                                (carrito as ArrayList<ListElement>).add(ic,
                                    element[x]
                                )
                                ic += 1
                            }

                        }

                    }

                }

                val listAdapter = CarritoAdapter(carrito, requireContext(), this)

                var recycleViewTodos : RecyclerView = view.findViewById(R.id.recycleViewCarrito)
                recycleViewTodos.setHasFixedSize(true)
                recycleViewTodos.layoutManager = LinearLayoutManager(requireContext()) //GridLayoutManager(requireContext(), 2)
                recycleViewTodos.adapter = listAdapter

            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito_compra, container, false)
    }

    companion object {

    }

    override fun onItemClickDelete(itemElemen: ListElement?) {

        if (itemElemen != null) {

            db.collection("Carrito")
                .get()
                .addOnSuccessListener { result ->

                    for(document in result) {

                        if( document.data.getValue("idProducto").toString() == itemElemen.idProducto ){
                            db.collection("Carrito").document(document.data.getValue("idProducto").toString()).delete()
                        }

                    }

                }

        }

    }
}
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
            Toast.makeText(requireContext().applicationContext, "A vaciado el carrito", Toast.LENGTH_LONG).show()
        }

        btnPagar.setOnClickListener {
            pagar()
            Toast.makeText(requireContext().applicationContext, "Pago Exitoso", Toast.LENGTH_LONG).show()
        }


    }

    private fun pagar() {

        db.collection("Carrito")
            .get()
            .addOnSuccessListener { result ->

                for(documentC in result) {

                    if(documentC.data.getValue("emailUser") == auth.currentUser?.email){
                        db.collection("Carrito").document(documentC.id).delete()
                        db.collection("Productos").document(documentC.data.getValue("idProducto").toString()).delete()
                    }

                }

            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG).show()
            }


        view?.findNavController()?.navigate(R.id.action_carritoCompraFragment_to_productFragment)

    }

    private fun vaciar() {


        db.collection("Carrito")
            .get()
            .addOnSuccessListener { result ->

                for(documentC in result) {

                    if(documentC.data.getValue("emailUser") == auth.currentUser?.email){
                        db.collection("Carrito").document(documentC.id).delete()
                    }

                }

            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

        view?.findNavController()?.navigate(R.id.action_carritoCompraFragment_to_productFragment)

    }

    private fun init(view:View, email:String){

        var i = 0

        element = ArrayList<ListElement>()
        carrito = ArrayList<ListElement>()

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

            db.collection("Carrito").get().addOnSuccessListener { result ->

                var ic = 0

                for( documentC in result ){

                    if( documentC.data.getValue("emailUser") == email ){

                        for( x in 0..element.size-1 ){

                            if( documentC.data.getValue("idProducto").toString() == element[x].idProducto ){
                                (carrito as ArrayList<ListElement>).add(ic,
                                    element[x]
                                )
                                ic += 1
                            }

                        }

                    }

                }

                var sumaTotal = 0

                if(carrito.size != 0){
                    for(i in carrito){
                        sumaTotal += i.precio
                    }

                    totalPagar.text = "Total a pagar: $"+sumaTotal

                    val listAdapter = CarritoAdapter(carrito, requireContext(), this)

                    var recycleViewTodos : RecyclerView = view.findViewById(R.id.recycleViewCarrito)
                    recycleViewTodos.setHasFixedSize(true)
                    recycleViewTodos.layoutManager = LinearLayoutManager(requireContext()) //GridLayoutManager(requireContext(), 2)
                    recycleViewTodos.adapter = listAdapter

                }



            }.addOnFailureListener { e ->
                Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG).show()
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

                    for(documentC in result) {

                        if(documentC.data.getValue("emailUser") == auth.currentUser?.email){
                            if( documentC.data.getValue("idProducto") == itemElemen.idProducto ){
                                db.collection("Carrito").document(documentC.id).delete()
                                val view2 = view
                                init(view2!!, auth.currentUser?.email!!)
                            }
                        }

                    }

                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG).show()
                }

        }

    }
}
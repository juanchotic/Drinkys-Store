package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.findNavController
import com.example.drinky.R
import com.example.drinky.view.iu.clases.ListElement
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.Serializable

class ProductDetailFragment : Fragment() {


    private lateinit var nombreProduct : TextView
    private lateinit var precioProduct : TextView
    private lateinit var imageProduct : ImageView
    private lateinit var descProduct : TextView
    private lateinit var btnAddCar : Button
    private lateinit var btnBackhome: ImageButton

    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    private lateinit var retornarA : String
    private lateinit var idProdut : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retornarA = ""
        idProdut = ""

        auth = Firebase.auth

        parentFragmentManager.setFragmentResultListener(
            "key",
            this,
            FragmentResultListener { requestKey: String, bundle: Bundle ->

                retornarA  = bundle.getString("volverA").toString()
                idProdut = bundle.getString("idProduct").toString()

                nombreProduct.text = bundle.getString("nombre")
                precioProduct.text = bundle.getString("precio")
                descProduct.text = bundle.getString("despc")

                  if(bundle.getString("categoria").toString() == "vino"){
                     imageProduct.setImageResource(R.mipmap.vino_dis_1);
                 }
                 else if(bundle.getString("categoria").toString() == "ancheta"){
                    imageProduct.setImageResource(R.mipmap.ancheta_dis_1);
                 }

             })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = auth.currentUser

        nombreProduct = view.findViewById(R.id.nombreTextView)
        precioProduct = view.findViewById(R.id.precioTextView)
        imageProduct = view.findViewById(R.id.imageProduct)
        descProduct = view.findViewById(R.id.descTextView)
        btnAddCar = view.findViewById(R.id.btnAddCarDetail)
        btnBackhome = view.findViewById(R.id.btnBachHome)

        btnBackhome.setOnClickListener {
            view : View ->

            if( retornarA == "Home" ){
                view.findNavController().navigate(R.id.action_productDetailFragment_to_productFragment)
            }
            else if( retornarA == "ProductView" ){
                view.findNavController().navigate(R.id.action_productDetailFragment_to_viewProductFragment)
            }

        }

        btnAddCar.setOnClickListener {

            if(currentUser != null){

                val datos = hashMapOf(
                    "emailUser" to currentUser.email,
                    "idProducto" to idProdut
                )

                var existe = false

                db.collection("Carrito").addSnapshotListener { snapshot, error ->

                    if( error != null ){
                        return@addSnapshotListener
                    }

                    for(document in snapshot!!){

                        if(document.data.getValue("idProducto") == idProdut && document.data.getValue("emailUser") == currentUser.email){
                            existe = true
                            break
                        }

                    }

                    if(!existe){
                        db.collection("Carrito")
                            .add(datos)
                            .addOnSuccessListener {
                                Toast.makeText(requireContext().applicationContext, "Agregado al carrito", Toast.LENGTH_LONG).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(requireContext().applicationContext, "Error - No fue agregado", Toast.LENGTH_LONG).show()
                            }
                    }
                    else{
                        Toast.makeText(requireContext().applicationContext, "Ya esta en el carrito", Toast.LENGTH_LONG).show()
                    }

                }

            }
            else {
                Toast.makeText(requireContext().applicationContext, "Debes iniciar secion", Toast.LENGTH_LONG).show()
            }

        }


    }

    companion object {

    }
}
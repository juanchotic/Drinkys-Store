package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinky.R
import com.example.drinky.view.iu.clases.ListAdapter
import com.example.drinky.view.iu.clases.ListAdapterHome
import com.example.drinky.view.iu.clases.ListElement
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ViewProductFragment : Fragment(), ListAdapter.OnItemClickListenerProduct  {

    private lateinit var element : List<ListElement>

    private lateinit var btnBackIni : ImageButton

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        element = ArrayList<ListElement>()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBackIni = view.findViewById(R.id.btnBackIni)

        init(view);

        btnBackIni.setOnClickListener {
                view : View ->

            view.findNavController().navigate(R.id.action_viewProductFragment_to_productFragment)


        }

    }

    private fun init(view:View) {

        var i = 0

        db.collection("Productos")
            .get()
            .addOnSuccessListener { result ->

                for(document in result){

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

                val listAdapter = ListAdapter(element, requireContext(), this)

                var recycleViewTodos : RecyclerView = view.findViewById(R.id.recycleViewProducto)
                recycleViewTodos.setHasFixedSize(true)
                recycleViewTodos.layoutManager = GridLayoutManager(requireContext(), 2)
                recycleViewTodos.adapter = listAdapter

            }
            .addOnFailureListener { e ->
                println("Error al subir los datos")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_product, container, false)
    }

    companion object {

    }

    override fun onItemClickProduct(itemElemen: ListElement) {
        println("Has dado click en " + itemElemen.nombre)

        var bundle = Bundle()

        bundle.putString("nombre", itemElemen.nombre)
        bundle.putString("precio", itemElemen.precio.toString())
        bundle.putString("despc", itemElemen.descripcion)
        bundle.putString("idProduct", itemElemen.idProducto)

        bundle.putString("volverA", "ProductView")
        bundle.putString("categoria", itemElemen.categoria)

        parentFragmentManager.setFragmentResult("key", bundle)

        view?.findNavController()?.navigate(R.id.action_viewProductFragment_to_productDetailFragment)
    }
}
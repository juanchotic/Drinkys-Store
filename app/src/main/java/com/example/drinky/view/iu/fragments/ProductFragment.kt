package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinky.R
import com.example.drinky.view.iu.clases.ListAdapter
import com.example.drinky.view.iu.clases.ListAdapterHome
import com.example.drinky.view.iu.clases.ListElement
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProductFragment : Fragment(), ListAdapterHome.OnItemClickListener  {

    private lateinit var element : List<ListElement>
    private lateinit var elementVinos : List<ListElement>
    private lateinit var elementAnchetas : List<ListElement>
    private lateinit var elementPopulares : List<ListElement>

    private lateinit var verMasVino: Button
    private lateinit var verMasAncheta: Button
    private lateinit var verMasPopular: Button
    private lateinit var verMasTodos: Button

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        element = ArrayList<ListElement>()
        elementVinos = ArrayList<ListElement>()
        elementAnchetas = ArrayList<ListElement>()
        elementPopulares = ArrayList<ListElement>()

    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view);

        verMasVino = view.findViewById(R.id.verMasVino)
        verMasAncheta = view.findViewById(R.id.verMasAncheta)
        verMasPopular = view.findViewById(R.id.verMasPopulare)
        verMasTodos = view.findViewById(R.id.verMasTodo)


        verMasVino.setOnClickListener{
                view: View ->

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
        }

        verMasAncheta.setOnClickListener{
                view: View ->

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
        }

        verMasPopular.setOnClickListener{
                view: View ->

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
        }

        verMasTodos.setOnClickListener{
                view: View ->

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
        }

    }

    private fun init(view:View) {

        var i = 0
        var anche = 0
        var vino = 0
        var popular = 0

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
                            document.data.getValue("descripcion").toString() )
                    )
                    i += 1

                    if( document.data.getValue("categoria").toString() == "vino" ){

                        (elementVinos as ArrayList<ListElement>).add(vino,
                            ListElement(
                                document.id,
                                document.data.getValue("precio").toString().toInt(),
                                document.data.getValue("popular").toString().toBoolean(),
                                document.data.getValue("nombre").toString(),
                                document.data.getValue("categoria").toString(),
                                document.data.getValue("descripcion").toString()
                            )
                        )
                        vino += 1

                        if( document.data.getValue("popular").toString().toBoolean() ){
                            (elementPopulares as ArrayList<ListElement>).add(popular,
                                ListElement(
                                    document.id,
                                    document.data.getValue("precio").toString().toInt(),
                                    document.data.getValue("popular").toString().toBoolean(),
                                    document.data.getValue("nombre").toString(),
                                    document.data.getValue("categoria").toString(),
                                    document.data.getValue("descripcion").toString()
                                )
                            )
                            popular += 1

                        }

                    }
                    else if( document.data.getValue("categoria").toString() == "ancheta" ){
                        (elementAnchetas as ArrayList<ListElement>).add(anche,
                            ListElement(
                                document.id,
                                document.data.getValue("precio").toString().toInt(),
                                document.data.getValue("popular").toString().toBoolean(),
                                document.data.getValue("nombre").toString(),
                                document.data.getValue("categoria").toString(),
                                document.data.getValue("descripcion").toString()
                            )
                        )
                        anche += 1

                        if( document.data.getValue("popular").toString().toBoolean() ){
                            (elementPopulares as ArrayList<ListElement>).add(popular,
                                ListElement(
                                    document.id,
                                    document.data.getValue("precio").toString().toInt(),
                                    document.data.getValue("popular").toString().toBoolean(),
                                    document.data.getValue("nombre").toString(),
                                    document.data.getValue("categoria").toString(),
                                    document.data.getValue("descripcion").toString()
                                )
                            )
                            popular += 1


                        }
                    }

                }

                val listAdapterHome = ListAdapterHome(element, requireContext(), this)
                val listAdapterHomeAnchetas = ListAdapterHome(elementAnchetas, requireContext(), this)
                val listAdapterHomeVinos = ListAdapterHome(elementVinos, requireContext(), this)
                val listAdapterHomePopulares = ListAdapterHome(elementPopulares, requireContext(), this)

                var recycleViewPopular : RecyclerView = view.findViewById(R.id.recycleViewProductoPopulare)
                recycleViewPopular.setHasFixedSize(true)
                recycleViewPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recycleViewPopular.adapter = listAdapterHomePopulares


                var recycleViewVino : RecyclerView = view.findViewById(R.id.recycleViewProductoVinos)
                recycleViewVino.setHasFixedSize(true)
                recycleViewVino.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recycleViewVino.adapter = listAdapterHomeVinos

                var recycleViewAncheta : RecyclerView = view.findViewById(R.id.recycleViewProductoAnchetas)
                recycleViewAncheta.setHasFixedSize(true)
                recycleViewAncheta.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recycleViewAncheta.adapter = listAdapterHomeAnchetas

                var recycleViewTodos : RecyclerView = view.findViewById(R.id.recycleViewProductoTodos)
                recycleViewTodos.setHasFixedSize(true)
                recycleViewTodos.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recycleViewTodos.adapter = listAdapterHome

            }
            .addOnFailureListener { e ->
                println("Error al subir los datos")
            }

    }

    override fun onItemClick(itemElemen: ListElement) {
        println("Has dado click en " + itemElemen.nombre)

        var bundle = Bundle()

        bundle.putString("nombre", itemElemen.nombre)
        bundle.putString("precio", itemElemen.precio.toString())
        bundle.putString("despc", itemElemen.descripcion)
        bundle.putString("idProduct", itemElemen.idProducto)

        bundle.putString("volverA", "Home")

        bundle.putString("categoria", itemElemen.categoria)

        parentFragmentManager.setFragmentResult("key", bundle)

        view?.findNavController()?.navigate(R.id.action_productFragment_to_productDetailFragment)
    }

    companion object {

    }

}
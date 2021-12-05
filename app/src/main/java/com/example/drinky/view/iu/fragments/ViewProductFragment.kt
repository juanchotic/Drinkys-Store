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


class ViewProductFragment : Fragment(), ListAdapter.OnItemClickListener  {

    private lateinit var element : List<ListElement>
    private lateinit var elementVinos : List<ListElement>
    private lateinit var elementAnchetas : List<ListElement>
    private lateinit var elementPopulares : List<ListElement>

    private lateinit var btnBackIni : ImageButton
    private lateinit var nombreProducto : TextView

    private lateinit var typeDatos : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        element = ArrayList<ListElement>()
        elementVinos = ArrayList<ListElement>()
        elementAnchetas = ArrayList<ListElement>()
        elementPopulares = ArrayList<ListElement>()

        typeDatos = ""

        parentFragmentManager.setFragmentResultListener(
            "key",
            this,
            FragmentResultListener { requestKey: String, bundle: Bundle ->

                typeDatos  = bundle.getString("typeDate").toString()

                if(typeDatos == "verVino"){
                    nombreProducto.text = "Vinos"
                }
                else if(typeDatos == "verAncheta"){
                    nombreProducto.text = "Anchetas"
                }
                else if(typeDatos == "verPopulare"){
                    nombreProducto.text = "Populares"
                }
                else{
                    nombreProducto.text = "Productos"
                }


            })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBackIni = view.findViewById(R.id.btnBackIni)
        nombreProducto = view.findViewById(R.id.nombreProductoVP)

        init(view);

        btnBackIni.setOnClickListener {
                view : View ->

            view.findNavController().navigate(R.id.action_viewProductFragment_to_productFragment)
        }

    }

    private fun llenarDatos( ){

        var desp : String = " awdazvzd wafwf wf afrew dkdnDN N I QIANDO DANFIA NFANINAOW FKWANFU V HEBG Uk cajfbu i ndafjiwebguvjnvuin vvjvuinv wbnf  vww bgiefjafnwiefn fwfn weiofnewn cwjenfwiuenf ej jewnf ij fwqk fweijnanbfwfw foiweanf efwioefn iuwnqakofnweiefvkmvs fnoiwfskl, kn foiwnm "

        for (i in 1..50){

            if( i % 3 == 0 ){

                var nom = "anchetas " + i

                if( i % 4 == 0 ){

                    // Populares anchetas
                    (element as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            true,
                            nom,
                            "ancheta",
                            "El producto " + nom + desp ))

                    (elementAnchetas as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            true,
                            nom,
                            "ancheta",
                            "El producto " + nom + desp ))

                    (elementPopulares as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            true,
                            nom,
                            "ancheta",
                            "El producto " + nom + desp ))

                }
                else{
                    (element as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            false,
                            nom,
                            "ancheta",
                            "El producto " + nom + desp ))

                    (elementAnchetas as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            false,
                            nom,
                            "ancheta",
                            "El producto " + nom + desp ))

                }

            }
            else{

                var nom = "vinos " + i

                if( i % 4 == 0 ){
                    // Populares vinos
                    (element as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            true,
                            nom,
                            "vino",
                            "El producto " + nom + desp ))

                    (elementVinos as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            true,
                            nom,
                            "vino",
                            "El producto " + nom + desp ))

                    (elementPopulares as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            true,
                            nom,
                            "vino",
                            "El producto " + nom + desp ))

                }
                else{
                    (element as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            false,
                            nom,
                            "vino",
                            "El producto " + nom + desp ))

                    (elementVinos as ArrayList<ListElement>).add(0,
                        ListElement(500*i,
                            true,
                            nom,
                            "vino",
                            "El producto " + nom + desp ))

                }

            }

        }

        /*
        (element as ArrayList<ListElement>).add(0, ListElement( "Producto 1", 500))
        (element as ArrayList<ListElement>).add(1, ListElement( "Producto 2", 600))
        (element as ArrayList<ListElement>).add(2, ListElement( "Producto 3", 700))
        (element as ArrayList<ListElement>).add(3, ListElement( "Producto 4", 800))
        (element as ArrayList<ListElement>).add(4, ListElement( "Producto 5", 1000))
        (element as ArrayList<ListElement>).add(5, ListElement( "Producto 6", 5800))
        (element as ArrayList<ListElement>).add(6, ListElement( "Producto 7", 5800))
        (element as ArrayList<ListElement>).add(7, ListElement( "Producto 8", 5800))
        (element as ArrayList<ListElement>).add(8, ListElement( "Producto 9", 5800))
        (element as ArrayList<ListElement>).add(9, ListElement( "Producto 10", 5800))
        (element as ArrayList<ListElement>).add(10, ListElement( "Producto 11", 5800))
        (element as ArrayList<ListElement>).add(11, ListElement( "Producto 12", 5800))
        (element as ArrayList<ListElement>).add(12, ListElement( "Producto 13", 5800))
        (element as ArrayList<ListElement>).add(13, ListElement( "Producto 14", 5800))
        (element as ArrayList<ListElement>).add(14, ListElement( "Producto 15", 5800))
        (element as ArrayList<ListElement>).add(15, ListElement( "Producto 16", 5800))
        (element as ArrayList<ListElement>).add(16, ListElement( "Producto 17", 5800))*/

    }

    private fun init(view:View) {

        llenarDatos()

        val listAdapter = ListAdapter(element, requireContext(), this)
        val listAdapterHomeAnchetas = ListAdapter(elementAnchetas, requireContext(), this)
        val listAdapterHomeVinos = ListAdapter(elementVinos, requireContext(), this)
        val listAdapterHomePopulares = ListAdapter(elementPopulares, requireContext(), this)


        if(typeDatos == "verVino"){

            var recycleViewVino : RecyclerView = view.findViewById(R.id.recycleViewProducto)
            recycleViewVino.setHasFixedSize(true)
            recycleViewVino.layoutManager = GridLayoutManager(requireContext(), 2)
            recycleViewVino.adapter = listAdapterHomeVinos

        }
        else if(typeDatos == "verAncheta"){

            var recycleViewAncheta : RecyclerView = view.findViewById(R.id.recycleViewProducto)
            recycleViewAncheta.setHasFixedSize(true)
            recycleViewAncheta.layoutManager = GridLayoutManager(requireContext(), 2)
            recycleViewAncheta.adapter = listAdapterHomeAnchetas

        }
        else if(typeDatos == "verPopulare"){

            var recycleViewPopular : RecyclerView = view.findViewById(R.id.recycleViewProducto)
            recycleViewPopular.setHasFixedSize(true)
            recycleViewPopular.layoutManager = GridLayoutManager(requireContext(), 2)
            recycleViewPopular.adapter = listAdapterHomePopulares

        }
        else{

            var recycleViewTodos : RecyclerView = view.findViewById(R.id.recycleViewProducto)
            recycleViewTodos.setHasFixedSize(true)
            recycleViewTodos.layoutManager = GridLayoutManager(requireContext(), 2)
            recycleViewTodos.adapter = listAdapter

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

    override fun onItemClick(itemElemen: ListElement) {
        println("Has dado click en " + itemElemen.nombre)

        var bundle = Bundle()

        bundle.putString("nombre", itemElemen.nombre)
        bundle.putString("precio", itemElemen.precio.toString())
        bundle.putString("despc", itemElemen.descripcion)

        bundle.putString("volverA", "ProductView")
        //bundle.putString("typeDatePV", typeDatos)

        parentFragmentManager.setFragmentResult("key", bundle)

        view?.findNavController()?.navigate(R.id.action_viewProductFragment_to_productDetailFragment)
    }
}
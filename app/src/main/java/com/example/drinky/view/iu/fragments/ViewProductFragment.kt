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


class ViewProductFragment : Fragment(), ListAdapter.OnItemClickListenerProduct  {

    private lateinit var element : List<ListElement>
    private lateinit var elementFiltros : List<ListElement>

    private lateinit var btnBackIni : ImageButton

    private lateinit var typeDatos : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        element = ArrayList<ListElement>()
        elementFiltros = ArrayList<ListElement>()

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

    private fun llenarDatos( ){

        var desp : String = " awdazvzd wafwf wf afrew dkdnDN N I QIANDO DANFIA NFANINAOW FKWANFU V HEBG Uk cajfbu i ndafjiwebguvjnvuin vvjvuinv wbnf  vww bgiefjafnwiefn fwfn weiofnewn cwjenfwiuenf ej jewnf ij fwqk fweijnanbfwfw foiweanf efwioefn iuwnqakofnweiefvkmvs fnoiwfskl, kn foiwnm "

        for (i in 0..50){

            if( i % 3 == 0 ){

                var nom = "anchetas " + i

                if( i % 4 == 0 ){

                    // Populares anchetas
                    (element as ArrayList<ListElement>).add(i,
                        ListElement(500*i,
                            true,
                            nom,
                            "ancheta",
                            "El producto " + nom + desp ))

                }
                else{
                    (element as ArrayList<ListElement>).add(i,
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
                    (element as ArrayList<ListElement>).add(i,
                        ListElement(500*i,
                            true,
                            nom,
                            "vino",
                            "El producto " + nom + desp ))

                }
                else{
                    (element as ArrayList<ListElement>).add(i,
                        ListElement(500*i,
                            false,
                            nom,
                            "vino",
                            "El producto " + nom + desp ))

                }

            }

        }

    }

    private fun init(view:View) {

        llenarDatos()

        val listAdapter : ListAdapter = ListAdapter(element, requireContext(), this)

        var recycleViewTodos : RecyclerView = view.findViewById(R.id.recycleViewProducto)
        recycleViewTodos.setHasFixedSize(true)
        recycleViewTodos.layoutManager = GridLayoutManager(requireContext(), 2)
        recycleViewTodos.adapter = listAdapter

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

        parentFragmentManager.setFragmentResult("key", bundle)

        view?.findNavController()?.navigate(R.id.action_viewProductFragment_to_productDetailFragment)
    }
}
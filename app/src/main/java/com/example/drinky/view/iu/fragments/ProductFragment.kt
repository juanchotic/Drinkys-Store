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


class ProductFragment : Fragment(), ListAdapterHome.OnItemClickListener  {

    private lateinit var element : List<ListElement>
    private lateinit var elementVinos : List<ListElement>
    private lateinit var elementAnchetas : List<ListElement>
    private lateinit var elementPopulares : List<ListElement>

    private lateinit var verMasVino: Button
    private lateinit var verMasAncheta: Button
    private lateinit var verMasPopular: Button
    private lateinit var verMasTodos: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        element = ArrayList<ListElement>()
        elementVinos = ArrayList<ListElement>()
        elementAnchetas = ArrayList<ListElement>()
        elementPopulares = ArrayList<ListElement>()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

            var bundle = Bundle()
            bundle.putString("typeDate", "verVino")
            parentFragmentManager.setFragmentResult("key", bundle)

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
        }

        verMasAncheta.setOnClickListener{
                view: View ->

            var bundle = Bundle()
            bundle.putString("typeDate", "verAncheta")
            parentFragmentManager.setFragmentResult("key", bundle)

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
        }

        verMasPopular.setOnClickListener{
                view: View ->

            var bundle = Bundle()
            bundle.putString("typeDate", "verPopulare")
            parentFragmentManager.setFragmentResult("key", bundle)

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
        }

        verMasTodos.setOnClickListener{
                view: View ->

            var bundle = Bundle()
            bundle.putString("typeDate", "verTodos")
            parentFragmentManager.setFragmentResult("key", bundle)

            view.findNavController().navigate(R.id.action_productFragment_to_viewProductFragment)
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

    override fun onItemClick(itemElemen: ListElement) {
        println("Has dado click en " + itemElemen.nombre)

        var bundle = Bundle()

        bundle.putString("nombre", itemElemen.nombre)
        bundle.putString("precio", itemElemen.precio.toString())
        bundle.putString("despc", itemElemen.descripcion)

        bundle.putString("volverA", "Home")
        bundle.putString("typeDatePV", "null")

        parentFragmentManager.setFragmentResult("key", bundle)

        view?.findNavController()?.navigate(R.id.action_productFragment_to_productDetailFragment)
    }

    companion object {

    }

}
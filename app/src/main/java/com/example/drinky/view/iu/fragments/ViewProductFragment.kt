package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinky.R
import com.example.drinky.view.iu.clases.ListAdapter
import com.example.drinky.view.iu.clases.ListElement


class ViewProductFragment : Fragment(), ListAdapter.OnItemClickListener  {

    private lateinit var element : List<ListElement>

    private lateinit var btnBackIni : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        element = ArrayList<ListElement>()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view);

        btnBackIni = view.findViewById(R.id.btnBackIni)

        btnBackIni.setOnClickListener {
                view : View ->
            view.findNavController().navigate(R.id.action_viewProductFragment_to_productFragment)
        }


    }

    private fun init(view:View) {
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
        (element as ArrayList<ListElement>).add(16, ListElement( "Producto 17", 5800))

        val listAdapter = ListAdapter(element, requireContext(), this)

        var recycleView : RecyclerView = view.findViewById(R.id.recycleViewProducto)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = listAdapter

    }

    private fun moveToDescription(item:ListElement ){

        var bundle = Bundle()

        bundle.putString("nombre", item.nombre)
        bundle.putString("precio", item.precio.toString())
        bundle.putSerializable("item", item)

        parentFragmentManager.setFragmentResult("key", bundle)

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
        bundle.putString("image", itemElemen.imagen)

        parentFragmentManager.setFragmentResult("key", bundle)

        view?.findNavController()?.navigate(R.id.action_viewProductFragment_to_productDetailFragment)
    }
}
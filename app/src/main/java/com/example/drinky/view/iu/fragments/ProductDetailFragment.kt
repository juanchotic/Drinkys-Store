package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.findNavController
import com.example.drinky.R
import com.example.drinky.view.iu.clases.ListElement
import java.io.Serializable

class ProductDetailFragment : Fragment() {


    private lateinit var nombreProduct : TextView
    private lateinit var precioProduct : TextView
    private lateinit var imageProduct : ImageView
    private lateinit var descProduct : TextView
    private lateinit var btnAddCar : Button
    private lateinit var btnBackhome: ImageButton

    private lateinit var typeDate : String
    private lateinit var retornarA : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        typeDate = ""
        retornarA = ""

        parentFragmentManager.setFragmentResultListener(
            "key",
            this,
            FragmentResultListener { requestKey: String, bundle: Bundle ->

                typeDate  = bundle.getString("typeDate").toString()
                retornarA  = bundle.getString("volverA").toString()

                nombreProduct.text = bundle.getString("nombre")
                precioProduct.text = bundle.getString("precio")
                descProduct.text = bundle.getString("despc")


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
                var bundle = Bundle()
                bundle.putString("typeDate", typeDate)
                parentFragmentManager.setFragmentResult("key", bundle)
                view.findNavController().navigate(R.id.action_productDetailFragment_to_viewProductFragment)
            }

        }


    }

    companion object {

    }
}
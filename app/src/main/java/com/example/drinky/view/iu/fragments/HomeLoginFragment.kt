package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.drinky.R

class HomeLoginFragment : Fragment() {

    private lateinit var btnLogin : Button
    private lateinit var btnRegister : Button
    private lateinit var btnHome : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin = view.findViewById(R.id.btnLogin)
        btnRegister = view.findViewById(R.id.btnRegister)
        btnHome = view.findViewById(R.id.btnIrHome)

        btnLogin.setOnClickListener {
                view : View ->
            view.findNavController().navigate(R.id.action_homeLoginFragment_to_loginFragment)
        }

        btnRegister.setOnClickListener {
                view : View ->
            view.findNavController().navigate(R.id.action_homeLoginFragment_to_registroFragment)
        }

        btnHome.setOnClickListener {
                view : View ->
            view.findNavController().navigate(R.id.action_homeLoginFragment_to_homeActivity)
        }


    }

    companion object {

    }
}
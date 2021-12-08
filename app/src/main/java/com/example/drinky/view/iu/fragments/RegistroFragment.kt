package com.example.drinky.view.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.drinky.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistroFragment : Fragment() {

    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var btnSignUp:Button
    private lateinit var btnSignIn:Button
    private lateinit var btnPhone:ImageButton

    private lateinit var btnBackHome:ImageButton

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            view?.findNavController()?.navigate(R.id.action_registroFragment_to_personActivity)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.inputUserR)
        pass = view.findViewById(R.id.inputPasswordR)
        btnSignIn = view.findViewById(R.id.btnSignInR)
        btnSignUp = view.findViewById(R.id.btnSignUpR)
        btnPhone = view.findViewById(R.id.btnTelefonoR)
        btnBackHome = view.findViewById(R.id.btnBackHomeRegiste)

        btnSignIn.setOnClickListener{
                view: View ->
            println("boton Sign in - Registro")

            view.findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
        }

        btnSignUp.setOnClickListener{
                view: View ->
            println("boton Sign UP  - Registro")

            createAccount(view, email.text.toString(), pass.text.toString())

        }

        btnPhone.setOnClickListener{
                view: View ->
            view.findNavController().navigate(R.id.action_registroFragment_to_loginTelefonoFragment)
        }

        btnBackHome.setOnClickListener{
                view: View ->
            view.findNavController().navigate(R.id.action_registroFragment_to_personActivity)
        }

    }

    private fun createAccount(view:View, email:String, password:String){
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        println("Creacion Con exito")
                        view.findNavController().navigate(R.id.action_registroFragment_to_homeActivity)
                    } else {
                        println("Creacion Fallida")
                        Toast.makeText(requireContext().applicationContext, "Usuaria ya Existe", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    companion object {

    }
}
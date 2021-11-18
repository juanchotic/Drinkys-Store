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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var btnSignIn:Button
    private lateinit var btnSignUp:Button
    private lateinit var btnPhone:ImageButton
    private lateinit var btnOlvPass:Button
    private lateinit var btnGoogle:ImageButton

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }

    }

    private fun reload() {
        //TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.inputUser)
        pass = view.findViewById(R.id.inputPassword)
        btnSignIn = view.findViewById(R.id.btnSignIn)
        btnSignUp = view.findViewById(R.id.btnSignUp)
        btnPhone = view.findViewById(R.id.btnTelefono)
        btnOlvPass = view.findViewById(R.id.btnOlvidePass)
        btnGoogle = view.findViewById(R.id.btnGoogle)

        btnSignIn.setOnClickListener{
                view: View ->
            println("boton Sign in")

            signIn(view, email.text.toString(), pass.text.toString())



        }

        btnSignUp.setOnClickListener{
                view: View ->
            println("boton Sign UP")

            view.findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

        btnPhone.setOnClickListener{
                view: View ->
            println("boton Telefono")
        }

        btnGoogle.setOnClickListener{
                view: View ->
            println("boton Google")
        }

        btnOlvPass.setOnClickListener{
                view: View ->
            println("boton Olvide contraseña")
        }

    }

    private fun signIn(view:View, email:String, password:String){
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(requireContext().applicationContext, "Error", Toast.LENGTH_LONG).show()
        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser

                        view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)

                    } else {
                        Toast.makeText(requireContext().applicationContext, "Usuario no valido", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
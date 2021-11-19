package com.example.drinky.view.iu.fragments

import android.content.Intent
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
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

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()

    }

    private fun updateUI(user: FirebaseUser?) {

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

        val currentUser = auth.currentUser
        if(currentUser != null){
            view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        }

        updateUI(currentUser)

        btnSignIn.setOnClickListener{
                view: View ->
            println("boton Sign in")

            signInEP(view, email.text.toString(), pass.text.toString())

        }

        btnSignUp.setOnClickListener{
                view: View ->
            println("boton Sign UP")

            view.findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

        btnPhone.setOnClickListener{
                view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_loginTelefonoFragment)
        }

        btnGoogle.setOnClickListener{
                view: View ->
            println("boton Google")
            signIn()
        }

        btnOlvPass.setOnClickListener{
                view: View ->
            println("boton Olvide contraseña")
        }

    }

    private fun signInEP(view:View, email:String, password:String){
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

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivity(signInIntent, RC_SIGN_IN)
    }

    private fun startActivity(signInIntent: Intent, rcSignIn: Int) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                println("Exitoza google")
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                println("Error google")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.

                }
            }
    }


    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
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
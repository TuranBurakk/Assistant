package com.infos.assistant.ui.login_screen

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.infos.assistant.R
import com.infos.assistant.data.UserData
import com.infos.assistant.databinding.FragmentLoginBinding
import com.infos.assistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = auth.currentUser

        if (user != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }

        binding.buttonSignUp.setOnClickListener {
            val eMail = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()
            viewModel.registerEmailAndPassword(eMail,password,requireContext())
            checkUser()
        }

        binding.signIn.setOnClickListener {
            val eMail = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()
            viewModel.loginEmailAndPassword(eMail,password,requireContext())
            checkUser()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.googleSignIn.setOnClickListener {
            googleSignIn()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.e(ContentValues.TAG, "Google sing in failed", e)
            }
        }

    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (task.result.additionalUserInfo!!.isNewUser){
                        db.collection("user").document(user!!.uid).set(UserData())
                    }
                    updateUI(user)

                } else {
                    updateUI(null)
                }
            }
    }
    private fun googleSignIn() {
        val singInIntent = googleSignInClient.signInIntent
        startActivityForResult(singInIntent, RC_SIGN_IN)
    }

    companion object {
        const val RC_SIGN_IN = 1001
        const val EXTRA_NAME = "EXTRA NAME"
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

    override fun onStart() {
        super.onStart()
        hideToolBar()
        hideBottomBar()
        hideTextview()
    }

    private fun checkUser(){
        if (auth.currentUser != null){
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

}
package com.infos.assistant.ui.login_screen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.infos.assistant.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() :ViewModel() {

    private val auth by lazy { Firebase.auth }
    private val db by lazy { Firebase.firestore }

    fun registerEmailAndPassword(email: String, password: String,context: Context) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful) {

                Toast.makeText(context, "Register Successful", Toast.LENGTH_LONG).show()
                db.collection("user").document(auth.currentUser!!.uid).set(UserData(email)).addOnCompleteListener {task ->
                }.addOnFailureListener {excepiton ->
                    Toast.makeText(context,excepiton.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }.addOnFailureListener {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun loginEmailAndPassword(email: String, password: String,context: Context) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            Toast.makeText(
                context,
                "Welcome ${auth.currentUser?.email.toString()}",
                Toast.LENGTH_LONG
            ).show()
        }.addOnFailureListener {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

}
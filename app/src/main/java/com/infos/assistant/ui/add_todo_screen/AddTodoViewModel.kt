package com.infos.assistant.ui.add_todo_screen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.infos.assistant.data.TodoData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel@Inject constructor() : ViewModel() {

    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    fun addTodo(todo:TodoData, context: Context){
        db.collection("user").document(auth.currentUser!!.uid).update("todo",FieldValue.arrayUnion(todo))
        Toast.makeText(context,"add successful",Toast.LENGTH_LONG).show()
    }
    
}
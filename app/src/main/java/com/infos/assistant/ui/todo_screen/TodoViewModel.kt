package com.infos.assistant.ui.todo_screen

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.infos.assistant.data.TodoData
import com.infos.assistant.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(): ViewModel() {
    val todoList = ArrayList<TodoData>()
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    init {
        getToDo()
    }

    private fun getToDo(){
        db.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            val user = it.toObject<UserData>()
            if (user != null ){
                for (item in user.Todo){
                    val id = item.uuid
                    val title = item.title
                    val explanation = item.explanation
                    val date = item.date
                    val isComplete = item.done
                    val todo = TodoData(id,title, explanation, date,isComplete)
                    todoList.add(todo)
                }
            }
        }

    }
    fun complete(todo: TodoData){
        when(todo.done){
            true -> todo.done = false
            false -> todo.done = true
        }
        db.collection("user").document(auth.currentUser!!.uid).update("done",todo.done)
    }
    fun deleteTask(todo: TodoData){
        db.collection("user").document(auth.currentUser!!.uid).update("todolist",FieldValue.arrayRemove(todo))
    }


}
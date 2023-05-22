package com.infos.assistant.ui.todo_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.infos.assistant.data.TodoData
import com.infos.assistant.data.UserData


class TodoViewModel : ViewModel() {
    private val _todo = MutableLiveData<List<TodoData>>()
    val todo: LiveData<List<TodoData>> = _todo
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    val todoList = mutableListOf<TodoData>()

    fun getToDo() {
        db.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject<UserData>()
            if (user != null) {
                todoList.clear()
                for (item in user.todo) {
                    val id = item.uuid
                    val title = item.title
                    val explanation = item.explanation
                    val date = item.date
                    val isComplete = item.done
                    val todo = TodoData(id, title, explanation, date, isComplete)
                    todoList.add(todo)
                }
                _todo.value = todoList
            }
        }
    }

    fun complete(todo: TodoData) {
        todo.done = !todo.done
        db.collection("user").document(auth.currentUser!!.uid).update("todo", todoList)
        getToDo()
    }

    fun deleteTask(todo: TodoData) {
        db.collection("user").document(auth.currentUser!!.uid).update("todo", FieldValue.arrayRemove(todo))
        getToDo()
    }
}
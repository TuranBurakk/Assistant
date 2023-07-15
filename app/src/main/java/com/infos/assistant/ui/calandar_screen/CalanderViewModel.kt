package com.infos.assistant.ui.calandar_screen

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.infos.assistant.R
import com.infos.assistant.data.TodoData
import com.infos.assistant.data.UserData
import java.util.*

class CalendarViewModel : ViewModel() {
    private val _todo = MutableLiveData<List<EventDay>>()
    val todo: LiveData<List<EventDay>> = _todo
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val todoList = mutableListOf<EventDay>()

    fun getToDo() {
        db.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject<UserData>()
            if (user != null) {
                todoList.clear()
                for (item in user.todo) {
                    val date = item.date
                    val calendar = Calendar.getInstance()
                    calendar.time = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(date)
                    val event = EventDay(calendar, R.drawable.ic_baseline_arrow_drop_up_24)
                    todoList.add(event)
                }
                _todo.value = todoList
            }
        }
    }
}
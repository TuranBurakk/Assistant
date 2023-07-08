package com.infos.assistant.ui.accounting_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.infos.assistant.data.AccountingData
import com.infos.assistant.data.UserData

class AccountingViewModel : ViewModel() {

    private val _accounting = MutableLiveData<List<AccountingData>>()
    val accounting: LiveData<List<AccountingData>> = _accounting
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val accountingList = mutableListOf<AccountingData>()

    fun getAccounting() {
        db.collection("user").document(auth.currentUser!!.uid).get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject<UserData>()
                if (user != null) {
                    accountingList.clear()
                    for (item in user.accounting) {
                        val date = item.date
                        val amount = item.amount
                        val description = item.description
                        val account = AccountingData(amount,date,description)
                        accountingList.add(account)
                    }
                    _accounting.value = accountingList
                }
            }
    }

    fun deleteAccounting(accounting:AccountingData){
        db.collection("user").document(auth.currentUser!!.uid).update("accounting", FieldValue.arrayRemove(accounting))
        getAccounting()
    }
}
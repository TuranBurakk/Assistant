package com.infos.assistant.ui.accounting_screen

import com.infos.assistant.data.AccountingData

interface IAccountingListener {
    fun delete(accounting:AccountingData)
    fun edit(accounting:AccountingData,updatedAmount: Int)
}
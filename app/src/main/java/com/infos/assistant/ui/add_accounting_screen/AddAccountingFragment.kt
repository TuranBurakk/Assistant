package com.infos.assistant.ui.add_accounting_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.infos.assistant.data.AccountingData
import com.infos.assistant.ui.base.BaseFragment
import com.infos.assistant.databinding.FragmentAddAccountingBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddAccountingFragment : BaseFragment<FragmentAddAccountingBinding>(FragmentAddAccountingBinding::inflate) {

    private val viewModel: AddAccountingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.expenseButton.setOnClickListener {
            if (!binding.amountEt.text.isNullOrEmpty()){
                val date = GregorianCalendar(binding.datePicker.year
                    ,binding.datePicker.month
                    ,binding.datePicker.dayOfMonth).time
                val amount = binding.amountEt.text.toString().toInt()
                val desc = binding.descEt.text.toString()
                val accounting = AccountingData(-amount,date,desc)
                viewModel.addAccounting(accounting,requireContext())
            }else Toast.makeText(requireContext(),"Amount cannot be empty",Toast.LENGTH_LONG).show()
        }

        binding.incomeButton.setOnClickListener {
            if (!binding.amountEt.text.isNullOrEmpty()){
                val date = GregorianCalendar(binding.datePicker.year
                    ,binding.datePicker.month
                    ,binding.datePicker.dayOfMonth).time
                val amount = binding.amountEt.text.toString().toInt()
                val desc = binding.descEt.text.toString()
                val accounting = AccountingData(amount,date,desc)
                viewModel.addAccounting(accounting,requireContext())

            }else Toast.makeText(requireContext(),"Amount cannot be empty",Toast.LENGTH_LONG).show()
        }
    }
}
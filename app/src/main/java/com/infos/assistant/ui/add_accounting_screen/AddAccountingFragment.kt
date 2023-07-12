package com.infos.assistant.ui.add_accounting_screen

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
            if (!binding.amountEt.text.isNullOrEmpty()) {
                val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
                val date = GregorianCalendar(
                    binding.datePicker.year,
                    binding.datePicker.month,
                    binding.datePicker.dayOfMonth
                ).time

                val formattedDate = dateFormatter.format(date)

                val amount = binding.amountEt.text.toString().toInt()
                val desc = binding.descEt.text.toString()
                val accounting = AccountingData(-amount, formattedDate, desc)
                viewModel.addAccounting(accounting, requireContext())
                findNavController().navigate(AddAccountingFragmentDirections.actionAddAccountingFragmentToAccountingFragment())
            } else {
                Toast.makeText(requireContext(), "Amount cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        binding.incomeButton.setOnClickListener {
            if (!binding.amountEt.text.isNullOrEmpty()) {
                val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
                val date = GregorianCalendar(
                    binding.datePicker.year,
                    binding.datePicker.month,
                    binding.datePicker.dayOfMonth
                ).time

                val formattedDate = dateFormatter.format(date)
                val amount = binding.amountEt.text.toString().toInt()
                val desc = binding.descEt.text.toString()
                val accounting = AccountingData(amount, formattedDate, desc)
                viewModel.addAccounting(accounting, requireContext())
                findNavController().navigate(AddAccountingFragmentDirections.actionAddAccountingFragmentToAccountingFragment())
            } else {
                Toast.makeText(requireContext(), "Amount cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
    }
}
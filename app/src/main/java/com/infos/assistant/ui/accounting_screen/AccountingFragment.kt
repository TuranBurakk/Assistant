package com.infos.assistant.ui.accounting_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.infos.assistant.data.AccountingData
import com.infos.assistant.databinding.FragmentAccountingBinding
import com.infos.assistant.ui.base.BaseFragment


class AccountingFragment : BaseFragment<FragmentAccountingBinding>(FragmentAccountingBinding::inflate),
IAccountingListener {

    private val viewModel : AccountingViewModel by viewModels()
    private val adapter by lazy { AccountingAdapter(this) }

    override fun onStart() {
        super.onStart()
        showTextview()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addAccountButton.setOnClickListener {
            findNavController().navigate(AccountingFragmentDirections.actionAccountingFragmentToAddAccountingFragment())
        }
        binding.accountingRecycler.adapter = adapter
        binding.accountingRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAccounting()
        viewModel.accounting.observe(viewLifecycleOwner){accounting ->
            adapter.setData(accounting)
            for (data in accounting){
                var total = 0
                val amount = data.amount ?: 0
                total += amount
                changeTextview(total)
            }

        }
    }


    override fun delete(accounting:AccountingData) {
       viewModel.deleteAccounting(accounting)
    }

    override fun edit(accounting: AccountingData,updatedAmount: Int) {
        viewModel.editAccounting(accounting,updatedAmount,requireContext())
    }
}
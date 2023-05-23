package com.infos.assistant.ui.accounting_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.infos.assistant.ui.base.BaseFragment
import com.infos.assistant.databinding.FragmentAccountingBinding


class AccountingFragment : BaseFragment<FragmentAccountingBinding>(FragmentAccountingBinding::inflate) {

    private val viewModel : AccountingViewModel by viewModels()
    private val adapter by lazy { AccountingAdapter() }

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
            var total = 0
            for (data in accounting){
                var amount = data.amount ?: 0
                total += amount
                changeTextview(amount)
            }


        }

    }
}
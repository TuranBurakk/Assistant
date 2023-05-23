package com.infos.assistant.ui.accounting_screen

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infos.assistant.data.AccountingData
import com.infos.assistant.databinding.AccountingRowBinding

class AccountingAdapter: RecyclerView.Adapter<AccountingAdapter.AccountingHolder>() {
    private var list = emptyList<AccountingData>()
    class AccountingHolder(val binding : AccountingRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountingHolder {
        val binding = AccountingRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AccountingHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountingHolder, position: Int) {
        holder.binding.apply {
            amountTv.text = list[position].amount.toString()
            descTv.text = list[position].description.toString()
            dateTv.text = list[position].date.toString()
        }
            if (list[position].amount!! < 0){
                holder.binding.cardView.setCardBackgroundColor(Color.RED)
                holder.binding.editButton.setBackgroundColor(Color.RED)
                holder.binding.deleteButton.setBackgroundColor(Color.RED)
            }else {
                holder.binding.cardView.setCardBackgroundColor(Color.GREEN)
                holder.binding.editButton.setBackgroundColor(Color.GREEN)
                holder.binding.deleteButton.setBackgroundColor(Color.GREEN)
            }
    }

    override fun getItemCount() = list.size

    fun setData(newList: List<AccountingData>) {
        list = newList
        notifyDataSetChanged()
    }
}